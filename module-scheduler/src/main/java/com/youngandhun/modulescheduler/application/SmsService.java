package com.youngandhun.modulescheduler.application;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youngandhun.modulecore.member.domain.Member;
import com.youngandhun.modulecore.member.exception.MemberErrorCode;
import com.youngandhun.modulecore.member.exception.MemberException;
import com.youngandhun.modulecore.member.repository.MemberRepository;
import com.youngandhun.modulescheduler.util.SmsCertificationUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SmsService {

	private final MemberRepository memberRepository;

	private final SmsCertificationUtil smsCertificationUtil;

	public void sendEncouragementMessage() {
		List<Member> members = memberRepository.findAll();

		members.forEach(
			member -> {
				LocalDate joinDate = LocalDate.from(member.getCreatedAt());
				LocalDate now = LocalDate.now();

				Period period = Period.between(joinDate, now);

				int monthsBetween = (period.getYears() * 12 + period.getMonths()) % 12; // 가입 기간

				String msg = switch (monthsBetween) {
					case 0, 3, 6, 9 -> getEncouragementMessage(0, member.getUsername());
					case 1, 4, 7, 10 -> getEncouragementMessage(1, member.getUsername());
					case 2, 5, 8, 11 -> getEncouragementMessage(2, member.getUsername());
					default -> throw new MemberException(MemberErrorCode.JOIN_DATE_NOT_FOUND);
				};

				smsCertificationUtil.sendMessage(member.getMobileNumber(), msg);
			}
		);
	}

	public String getEncouragementMessage(int msgIdx, String username) {
		return switch (msgIdx) {
			case 0 -> // 3n개월
				username + "님께\n" +
					"""
						모든 일이 순조롭게 진행되고 있기를 바랍니다.
						누군가와 대화가 필요하다면 24시간 전화(1588-9191)가 있으며 도움이 필요한 경우 헬프라인 직원에게 연락할 수 있습니다.
						본 문자메세지는 회신할 수 없습니다.
						""";
			case 1 -> // 3n+1개월
				username + "님께\n" +
					"""
						안녕하세요.
						지난번 연락 이후로 평안하셨길 바랍니다. 잘 지내고 있나요?
						누군가와 대화하고 싶거나(1588-9191) 지역 보건 서비스의 도움이 필요할 경우(129)를 대비해 24시간 전화가 준비되어 있습니다.
						""";
			case 2 -> // 3n+2개월
				username + "님께\n" +
					"""
						잘 지내고 있나요? 도움이 필요하시다면 연락하세요.
						""";
			default -> "";
		};
	}

}
