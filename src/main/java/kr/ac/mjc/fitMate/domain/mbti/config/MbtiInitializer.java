package kr.ac.mjc.fitMate.domain.mbti.config;

import kr.ac.mjc.fitMate.domain.mbti.entity.Mbti;
import kr.ac.mjc.fitMate.domain.mbti.repository.MbtiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MbtiInitializer implements CommandLineRunner {

    private final MbtiRepository mbtiRepository;

    @Override
    public void run(String... args) {
        if (mbtiRepository.count() == 0) {

            // 1. ENFP
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ENFP")
                            .subtitle("재기발랄한 자유로운 영혼")
                            .imageUrl("/image/mbti/ENFP.png")  // 이미지 경로
                            .mainDescription("열정적이고 창의적인 당신은 새로운 가능성을 탐구하는 것을 좋아합니다.\n사람들과의 깊은 교류를 중시하며, 진정성 있는 관계를 추구합니다.")
                            .tag1("#공감능력")
                            .tag2("#열정적")
                            .tag3("#창의적")

                            .basicTendency("ENFP는 호감이 생기면 적극적으로 다가가는 편입니다. 재치있는 대화와 깊은 질문으로 상대방을 알아가려 하며, 때로는 너무 빠르게 감정을 드러내기도 합니다. 감정 표현이 풍부하고 솔직해서 상대방이 자신의 마음을 쉽게 알아차릴 수 있어요.")
                            .chatStyle("감정을 솔직하게 표현하고, 상대방의 이야기를 경청합니다. 깊이 있는 대화를 선호하며, 철학적이고 감성적인 주제를 즐깁니다.")
                            .loveExpression("작은 서프라이즈와 깜짝 선물을 좋아합니다. 상대방의 특별한 순간을 기억하고 의미있게 만들어주려 노력해요.")
                            .tiredPeople("규칙적인 연락보다는 감정이 동할 때 깊이있는 대화를 나누는 것을 선호합니다. 오랜만에 만나도 어색하지 않고, 마치 어제 본 것처럼 편안한 관계를 만들어갑니다.")

                            .stressReaction("감정이 격해지면 충동적으로 말할 수 있음||혼자 있는 시간을 가지며 생각 정리||때로는 문제를 회피하고 싶어함||과도하게 자책하거나 감정에 휩싸임")
                            .recoveryMethod("신뢰하는 사람과 깊은 대화 나누기||창의적 활동으로 감정 표출하기||자연 속에서 시간 보내기||새로운 경험으로 에너지 충전하기")
                            .stressNote("갈등 상황에서는 감정이 앞서기 쉬우니, 한 템포 쉬어가는 것이 중요해요. 솔직함은 유지하되, 상대의 입장에 대한 질문과 공감을 먼저 건네보세요.")

                            .goodMatch("INFJ||INTJ||ENFJ")
                            .goodMatchDescription("ENFP는 영감과 감성 기반 플러팅이 강한데 INFJ/INTJ는 본질 질문과 장기 비전으로 균형을 맞춰줍니다. ENFJ와도 공감 주제와 열정의 언어가 비슷해 관계가 자주 충전되고 오래가요.")
                            .cautionMatch("ISTJ||ISFJ||ESTJ")
                            .cautionMatchDescription("S형+J형은 변화를 줄이고 루틴과 안정 중심이라 ENFP의 즉흥과 아이디어 확장이 부담이 될 수 있어요. 사랑의 언어 속도와 주제(영감 vs. 안정)가 충돌하면 오해가 잦아집니다.")
                            .build()
            );

            // 2. INFP
            mbtiRepository.save(
                    Mbti.builder()
                            .type("INFP")
                            .subtitle("조용하지만 깊은 이상주의자")
                            .imageUrl("/image/mbti/INFP.png")
                            .mainDescription("섬세한 감정을 지닌 당신은 마음의 가치를 옳은 기준으로 삼습니다.\n말은 적지만 사랑과 관계에 누구보다 진심입니다.")
                            .tag1("#따뜻한감성")
                            .tag2("#내면집중")
                            .tag3("#진정성")

                            .basicTendency("INFP는 호감이 있어도 조심스럽게 다가가는 편입니다. 직접적인 고백보다는 진심 어린 행동과 은근한 표현으로 마음을 전하며, 상대의 반응을 오래 관찰합니다. 감정이 깊고 섬세해서 작은 말에도 쉽게 흔들릴 수 있어요.")
                            .chatStyle("내면의 감정을 신중하지만 솔직하게 표현합니다. 말은 차분해도 분위기는 따뜻하며, 감정·가치·이상에 대해 이야기 나누는 것을 좋아합니다.")
                            .loveExpression("감동 포인트를 디테일하게 챙기는 편입니다. 의미 있는 선물, 공감의 말, 진심 담긴 위로와 격려로 애정을 표현해요.")
                            .tiredPeople("자주 연락하지 않아도 연결이 유지되는 소수 정예 스타일입니다. 깊은 감정선이 공유된 관계라면 오래 만나지 않아도 마음의 거리가 멀어지지 않습니다.")

                            .stressReaction("내부 갈등이 커지면 감정을 말로 꺼내기 어려움||혼자 잠수하며 과생각||나의 이상과 현실의 괴리에 피로감||작은 말에도 크게 상처받음")
                            .recoveryMethod("글로 감정 정리하기||나만의 가치가 존중되는 대화 나누기||음악·그림 같은 예술로 표현||조용하고 안전한 공간 확보")
                            .stressNote("상대의 반응을 해석하느라 지치지 말고, 당신의 감정을 직접 문장으로 설명해보세요. “난 이렇게 느꼈어” 한 문장이 관계의 깊이를 지켜줍니다.")

                            .goodMatch("ENFJ||ENTJ||ENFP")
                            .goodMatchDescription("ENFJ는 INFP의 감정을 심화 질문으로 어루만지고 성장 프레임으로 지켜줍니다. ENTJ는 목표 공유로 INFP의 이상 실현을 도와주며, ENFP와는 진정성 있는 감정 교환 코드가 같아 마음의 문을 빠르게 열 수 있어요.")
                            .cautionMatch("ESTP||ISTP||ESTJ")
                            .cautionMatchDescription("실행 직진형(STP)은 감정의 이유 탐구 시간을 불필요하게 느낄 수 있습니다. ESTJ는 원칙 기반이라 INFP의 감정 동기 중심 언어를 합리성 부족으로 오해할 가능성이 높아요.")
                            .build()
            );

            // 3. ENTP
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ENTP")
                            .subtitle("재치 넘치는 아이디어 토론러")
                            .imageUrl("/image/mbti/ENTP.png")
                            .mainDescription("대화를 탐구처럼 즐깁니다.\n사랑도 지적 자극과 밀당 안에서 더 타오릅니다.")
                            .tag1("#설득력")
                            .tag2("#논쟁의매력")
                            .tag3("#창의적사고")

                            .basicTendency("ENTP는 호감이 생기면 말로 밀당합니다. 장난 섞인 도발과 재치로 상대 반응을 끌어내고, 지적 자극으로 접근해요. 토론처럼 싸우는 순간도 매력 포인트로 작동할 수 있는 유형입니다.")
                            .chatStyle("호기심 기반의 질문 폭격(?)과 관점 던지기로 소통합니다. 감정 표현도 아이디어처럼 맛있게(?) 전달해요.")
                            .loveExpression("특별한 데이트 설계, 말로 건네는 플러팅, 재미있는 선물로 사랑을 채웁니다. 지루함 금지!")
                            .tiredPeople("관계도 아이디어 연결입니다. 주제·관점으로 묶인 대화가 이어지면 오래갑니다.")

                            .stressReaction("논쟁 모드 과잉||비꼼, 냉소↑||말로 이기려 함||상대 도발 가능성")
                            .recoveryMethod("브레인스토밍형 대화||재미있는 경험 만들기||새로운 아이디어 전개||스릴 있는 액티비티")
                            .stressNote("이기는 말보다 ‘질문하는 말’이 더 매력 강력! “넌 왜 그렇게 느꼈어?” → 갈등해소 치트키")

                            .goodMatch("INFJ||INTJ||INTP")
                            .goodMatchDescription("ENTP는 관점을 확장하는 토론 기반 질문으로 다가가요. INFJ/INTJ는 본질 기반 질문으로 균형을 잡아주고, INTP와도 개념 기반 토론 언어가 같아서 말의 시그널 오차가 적습니다.")
                            .cautionMatch("ISFJ||ESFJ||INFP")
                            .cautionMatchDescription("결론보다 감정선을 먼저 챙기는 대화를 선호해서, ENTP의 토론 접근이 ‘의도 평가/도발 신호’로 해석되어 불안이나 서운함으로 이어질 수 있어요.")
                            .build()
            );

            // 4. INTP
            mbtiRepository.save(
                    Mbti.builder()
                            .type("INTP")
                            .subtitle("감정을 논리로 해석하는 사색가")
                            .imageUrl("/image/mbti/INTP.png")
                            .mainDescription("관찰하고 분석하며 감정을 해석합니다.\n고독 속에서 사랑의 방식을 정리합니다.")
                            .tag1("#혼자충전")
                            .tag2("#분석주의")
                            .tag3("#아이디어중심")

                            .basicTendency("INTP는 좋아해도 티가 늦게 나는 편입니다. 감정을 직접 말하진 않아도 상대 패턴과 말투를 분석합니다. 부담스러운 감정 요구가 많으면 혼자 거리 조정이 필요해요.")
                            .chatStyle("차분하고 분석적입니다. 감정보단 “왜 그렇게 느꼈을까?” 라는 개념과 구조에 집중해요.")
                            .loveExpression("시간 공유와 존중이 곧 사랑입니다. 같이 있지만 서로의 공간을 허용하는 방식이 애정 표현이에요.")
                            .tiredPeople("필요할 때만 존재감 발휘합니다. 자주 연락 안 해도 사고의 연결로 유지되는 관계를 선호해요.")

                            .stressReaction("감정 요구 과다 시 거리 조정||과고립, 말수 ↓||감정을 개념으로 풀려고 함||내부에서 상황 분석 과잉")
                            .recoveryMethod("혼자 생각 충전||구조적 질문 주고받기||사실 기반 소통||조용한 실내 공간")
                            .stressNote("분석은 장점이지만 관계에서는 마지막에 “그래서 난 이렇게 느꼈어” 한 문장을 꼭 붙여봐요.")

                            .goodMatch("ENTJ||ESTJ||ISTP")
                            .goodMatchDescription("NTP는 분석과 개념 중심 언어가 관계 언어입니다. ENTJ/ESTJ는 설계·효율 질문으로 방향과 결론을 명백히 정리해줘 부담이 적고, ISTP와도 과정보단 구조와 해결 중심이라 공백이 적어요.")
                            .cautionMatch("ESFJ||ENFJ||ESFP")
                            .cautionMatchDescription("감정 리액션 중심(E*FJ/FP)은 INTP에게 과부하로 느껴져 관계 이유 해석 시간이 더 필요해질 수 있고, 모멘텀 빈도와 온도차에서 오해 가능성이 올라갑니다.")
                            .build()
            );

            // 5. ENFJ
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ENFJ")
                            .subtitle("사람을 비전으로 이끄는 선도자")
                            .imageUrl("/image/mbti/ENFJ.png")
                            .mainDescription("사람의 잠재력을 빠르게 읽고 성장의 방향을 제시합니다.\n깊은 유대와 조화를 통해 진심 어린 연결을 만들어갑니다.")
                            .tag1("#카리스마")
                            .tag2("#공감리더십")
                            .tag3("#관계지향")

                            .basicTendency("ENFJ는 호감이 생기면 상대의 감정선을 정확히 읽고 부드럽게 다가갑니다. 부담을 주지 않으려 하지만 리드하는 편이며, 상대의 고민과 감정까지 세심하게 캐치합니다. 마음을 읽히기 쉬운 유형이에요.")
                            .chatStyle("공감 + 격려 + 방향제시가 조합된 대화를 구사합니다. 진심으로 경청하며, 사람의 성장을 돕는 감성적이고 따뜻한 대화가 특징입니다.")
                            .loveExpression("감정을 말과 행동으로 책임감 있게 표현합니다. 특별한 날을 기억하고, 함께 의미 있는 목표를 세워주는 것도 애정 방식이에요.")
                            .tiredPeople("관계를 ‘팀’처럼 생각하고 돌봅니다. 정서적 안정과 신뢰를 꾸준히 유지하며, 서로 더 나아질 수 있도록 돕는 연결을 지향합니다.")

                            .stressReaction("타인의 감정까지 짊어져 책임 과부하||갈등 중 과중재로 피로||인간 관계를 완벽히 관리해야 한다는 압박||속상함이 누적되면 감정 폭발")
                            .recoveryMethod("감사와 진심을 주고받는 1:1 대화||계획 재정리와 목표 재설계||사람의 성장 사례로 스스로 격려||혼자 조용히 리프레시")
                            .stressNote("조화를 맞추려다 본인 감정을 잊지 마세요. 도와주려는 말 대신 ‘먼저 이해하는 말’로 접근해보세요. 리드보다 공감이 관계 회복에 빠릅니다.")

                            .goodMatch("INFP||ISFP||ENFP")
                            .goodMatchDescription("ENFJ는 상대 감정을 세심히 읽고 조율해 INFP의 깊은 내면 감성과 강한 정서적 연결이 가능합니다. ISFP와는 감성을 존중하며 편안한 친밀감을 유지하고, ENFP와는 열정과 공감의 에너지가 조화롭게 맞물려 관계 모멘텀이 크고 지속력이 높아요.")
                            .cautionMatch("ISTP||ESTP||ISTJ")
                            .cautionMatchDescription("ISTP와 ESTP는 감정 대화보다 즉각 실행과 해결을 우선해 ENFJ의 조율 과정이 답답하게 느껴질 수 있습니다. ISTJ는 원칙과 구조 기반이라 감정 흐름 중심의 소통이 피로로 해석될 수 있어 오해가 생기기 쉬워요.")
                            .build()
            );
            // 6. INFJ
            mbtiRepository.save(
                    Mbti.builder()
                            .type("INFJ")
                            .subtitle("통찰로 사랑을 완성하는 옹호자")
                            .imageUrl("/image/mbti/INFJ.png")
                            .mainDescription("겉은 조용하지만 마음은 깊은 레이더로 가득합니다.\n마음의 본질과 미래를 함께 설계합니다.")
                            .tag1("#직관적공감")
                            .tag2("#관계의심화")
                            .tag3("#장기비전")

                            .basicTendency("INFJ는 호감이 생기면 조용하지만 매우 깊게 스며드는 편입니다. 상대의 진심과 숨은 감정을 빠르게 읽어내지만, 표현은 신중하며 쉽게 마음을 열지 않아요. 하지만 한번 꽂히면 진짜 오래 갑니다.")
                            .chatStyle("통찰 기반의 공감형 대화를 합니다. 감정의 본질을 파악하는 질문을 던지고, 철학적이고 의미 중심의 대화를 즐겨요.")
                            .loveExpression("말보다는 진심의 무게로 표현합니다. 상대만의 본질을 기억하고 존중하며, 깊고 사려 깊은 방식으로 마음을 전달해요.")
                            .tiredPeople("깊은 1:1 연결을 지향합니다. 형식적인 연락은 적지만, 긴 대화 하나로 관계의 밀도를 유지합니다.")

                            .stressReaction("감정 과부하 → 도어슬램 가능성||혼자 과몰입해서 시나리오 설계||기대-현실 차이로 에너지 저하||사람에게 실망하면 급차단 모드")
                            .recoveryMethod("의미와 본질 중심의 대화||조용한 공간에서 생각 정리||미래 약속 재설정||신뢰 기반 소규모 연결")
                            .stressNote("관계를 극단 설계로 정리하지 말고, 감정을 ‘관찰’이 아닌 ‘설명’으로 쓰세요. 질문으로 상대 내면을 다시 열어보세요.")

                            .goodMatch("ENTP||ENFP||INFP")
                            .goodMatchDescription("INFJ는 감정 본질과 맥락을 읽으며 질문으로 연결을 지켜요. ENTP/ENFP의 아이디어 확장을 의미 질문으로 균형을 잡아주고, INFP의 감성 언어와도 방향이 같아 1:1 심화 대화로 유대가 오래가요.")
                            .cautionMatch("ESTP||ISTP||ESFP")
                            .cautionMatchDescription("빠른 액션/리액션 중심(E*SFP, *STP)은 INFJ의 깊이 조율 과정이 “속도감 부족”으로 느껴질 수 있고, 충동 모멘텀 차이로 섬세한 의도 해석에서 오차가 생깁니다.")
                            .build()
            );

            // 7. ENTJ
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ENTJ")
                            .subtitle("목표로 관계를 정복하는 통솔자")
                            .imageUrl("/image/mbti/ENTJ.png")
                            .mainDescription("사랑도 팀워크이자 프로젝트입니다.\n구조와 목표 안에서 최고의 시너지를 설계합니다.")
                            .tag1("#전략적리더")
                            .tag2("#단호함")
                            .tag3("#성취지향")

                            .basicTendency("ENTJ는 좋아하면 숨기지 않습니다. 다만 감정 언어보다 실행과 리드 중심으로 다가가요. 모호함과 비효율을 싫어해 관계의 흐름이 답답하면 빠르게 정리하려 합니다.")
                            .chatStyle("카리스마 + 사실 + 목표 기반의 언어입니다. 감정도 관리하듯, 해결과 방향 제시를 섞어요.")
                            .loveExpression("목표와 약속을 철저히 기억합니다. 함께 성취 플랜을 짜주거나 직접 도움으로 사랑을 표현해요.")
                            .tiredPeople("사랑도 삶도 ‘팀 빌딩’입니다. 명확한 소통, 책임감, 목표공유 기반으로 신뢰 관계를 완성합니다.")

                            .stressReaction("비효율에 짜증↑||통제과잉||감정 무시해서 더 싸우게 됨||목표 상실 시 피로")
                            .recoveryMethod("구조 정리||목표 공유형 대화||즉각 문제 해결||사람과 목표를 재정립")
                            .stressNote("리드보다 이해! 해결보다 공감 먼저! 그 다음 플랜 실행!")

                            .goodMatch("INTJ||INTP||ENFJ")
                            .goodMatchDescription("INTJ와는 장기 목표 설계 및 의사결정 속도가 비슷해 대화에 군더더기가 없고 실행까지 빠르게 이어집니다. INTP와는 개념 기반 토론과 논리 흐름이 자연스럽게 맞물려 감정 소모 없이도 신뢰 구조가 생겨요. ENFJ와는 비전 제시(ENTJ)와 공감 리드(ENFJ)가 균형을 이루며, 한 사람이 큰 그림을 정리하면 다른 사람이 감정선을 받쳐주어 ‘팀 같은 시너지’가 강합니다.")
                            .cautionMatch("ESFP||ISFP||ESTP")
                            .cautionMatchDescription("(ESFP, ISFP)는 감각·분위기·즉흥 타이밍이 강점이라 ENTJ의 결론 중심 리드가 ‘과한 통제/속도 압박’ 신호로 읽히기 쉽습니다. ESTP는 액션 직진은 잘 맞지만 갈등 중 감정 탐색 시간이 거의 없어, ENTJ 입장에서는 ‘맥락 공백’으로 보이고 ESTP 입장에서는 ‘감정 비효율 요구’로 느껴 서로 방향성 오해가 생길 수 있어요.")
                            .build()
            );

            // 8. INTJ
            mbtiRepository.save(
                    Mbti.builder()
                            .type("INTJ")
                            .subtitle("사랑도 시스템으로 설계하는 전략가")
                            .imageUrl("/image/mbti/INTJ.png")
                            .mainDescription("예감보다 논리가, 설렘보다 설계가 우선입니다.\n깊게 사랑하지만 방식은 효율적입니다.")
                            .tag1("#냉철한분석")
                            .tag2("#독립적")
                            .tag3("#장기플랜")

                            .basicTendency("INTJ는 호감이 생기면 계산된 직진입니다. 감정 표현은 절제하지만 전략적으로 다가가요. 불필요한 감정 소모를 싫어해서, 관계의 방향성이 흐릿하면 빠르게 선을 긋기도 합니다.")
                            .chatStyle("감정보다는 개념과 분석 기반의 언어입니다. 미래 시나리오와 개선점을 던지는 방식이 특징이에요.")
                            .loveExpression("사랑도 ‘장기 플랜’ 안에 있습니다. 미래 약속, 용서의 확장성, 목표 공유로 애정을 표현해요.")
                            .tiredPeople("촘촘하지 않아도 오래 지속되는 ‘소수정예 신뢰형 유대’입니다. 큰 틀의 연결 구조에서 안정감을 느껴요.")

                            .stressReaction("혼자 극단 계획에 과몰입||완벽주의로 피곤||감정 비효율에 냉랭||답답하면 급선그음")
                            .recoveryMethod("프라이빗 공간||미래 개선 플랜 대화||필요한 말만 주고받기||조용한 충전")
                            .stressNote("답을 내리려 하기 전, “준비되지 않은 감정은 실수도 과정임”을 기억하세요.")

                            .goodMatch("ENTP||INFJ||ISTJ")
                            .goodMatchDescription("INTJ는 주도적으로 큰 그림을 설계해 질문으로 방향을 정리합니다. ENTP와는 관점을 주고받는 질문 코드가 맞아 토론도 엔진이 되고, INFJ/ISTJ와는 본질-구조 중심 질문으로 균형이 잘 맞아요.")
                            .cautionMatch("ESFP||ISFP||ENFP")
                            .cautionMatchDescription("감정 기반 모멘텀 타이밍과 INTJ의 사전 설계 타이밍 간 시차가 생겨, INTJ의 공백이 상대 입장에서 “리액션 공백”으로 해석될 수 있어요.")
                            .build()
            );

            // 9. ESFP
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ESFP")
                            .subtitle("사랑도 인생도 축제처럼!")
                            .imageUrl("/image/mbti/ESFP.png")
                            .mainDescription("사람들과 어울리는 순간이 충전입니다.\n사랑을 즐겁고 뜨겁게, 인생을 무대처럼 살아갑니다.")
                            .tag1("#즐거움")
                            .tag2("#자유분방")
                            .tag3("#스포트라이트")

                            .basicTendency("ESFP는 좋아지면 세상에 소문나는(?) 편입니다. 즉흥 데이트 제안, 뜨거운 리액션, 함께 웃는 순간 만들기로 접근해요. 감정 표현 모멘텀 폭발")
                            .chatStyle("감정 솔직 + 긍정 리액션이 특징입니다. 에너지 넘치고 다정+재치 균형형")
                            .loveExpression("같이 즐기고 웃고 놀아주는 것이 사랑입니다. 특별한 경험 공유!")
                            .tiredPeople("오랜만에 만나도 바로 축제처럼 편합니다. 즐거움 기반 연결.")

                            .stressReaction("산만해지고 감정 호소성 말↑||과사교로 방전됨||충동적 선택 증가||지치면 급우울/짜증")
                            .recoveryMethod("사람과의 즐거운 경험으로 회복||짧고 빈도 높은 감정 표현 OK||여행, 파티, 같이 웃기||신나는 액티비티")
                            .stressNote("즐거움으로 푸는 건 장점이지만, “왜 화났어?” 같은 본질 질문도 툭 던지면 관계 밀도 UP!")

                            .goodMatch("ISTP||ISFJ||ESTP")
                            .goodMatchDescription("ESFP는 말+행동 리액션 모멘텀으로 다가가요. ISTP/ESTP는 해결 직진형이라 데이트 즉시 행동이 화해가 되고, ISFJ는 “말했던 문장”을 기억하고 감각을 행동으로 챙겨주어 ESFP의 감정선을 오래 지켜줍니다.")
                            .cautionMatch("INTJ||INTP||INFJ")
                            .cautionMatchDescription("감정 모멘텀 대신 분석/통찰 시나리오를 혼자 정리하면서 다가가서, 모멘텀 충전 시간차가 생겨 ESFP 입장에서 “리액션 공백”으로 해석될 수 있어요.")
                            .build()
            );

            // 10. ISFP
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ISFP")
                            .subtitle("감성을 색으로 표현하는 분위기의 장인")
                            .imageUrl("/image/mbti/ISFP.png")
                            .mainDescription("섬세한 표현과 분위기를 사랑의 언어로 씁니다.\n감정이 예술이자, 당신의 고유한 대화법입니다.")
                            .tag1("#즉흥적")
                            .tag2("#예술적감각")
                            .tag3("#자유로운감성")

                            .basicTendency("ISFP는 말보다 분위기와 감각으로 접근합니다. 함께하는 공간의 온도, 음악, 시선을 감성으로 쓰며, 상대가 특별하다고 느끼게 조용히 설계해요.")
                            .chatStyle("감정을 비유와 분위기로 표현합니다. 솔직하지만 부드럽게 스며들어요.")
                            .loveExpression("기억한 특별 순간 만들기, 감성 디테일이 담긴 선물을 좋아합니다.")
                            .tiredPeople("조용히 유지되지만 어색함이 안 생깁니다. 관계가 편안한 색으로 오래 남아요.")

                            .stressReaction("감정을 억누르며 회피||혼자 속상한 감정선을 예술처럼 소화||사람 과부하 시 잠수||충동 소비로 해소할 수도")
                            .recoveryMethod("그림, 음악, 사진, 감성 활동||자연 속 산책||의미 담긴 작은 경험 만들기||분위기 좋은 공간")
                            .stressNote("“지금 마음 상태는 ○○색 같아” 같은 비유 한 줄이면 당신의 언어로 갈등을 풀 수 있어요.")

                            .goodMatch("ESFJ||ENFJ||ESTP")
                            .goodMatchDescription("ISFP는 분위기·감각·눈빛 언어가 강해 E*FJ/ESFJ의 공감 리액션과 디테일 케어가 마음의 표현 속도를 안정적으로 맞춰줍니다. ESTP는 함께하는 현재 케미와 즉각 행동으로 감정을 부담 없이 풀어줘 조화가 강해요.")
                            .cautionMatch("INTJ||ENTJ||INTP")
                            .cautionMatchDescription("감정 조율 과정 대신 구조 설계를 먼저 던져서 ISFP의 감각 기반 타이밍과 충돌이 잦아요. INTP는 표현보다 개념 중심이라 감성 리액션 해석에서 오차가 발생합니다.")
                            .build()
            );

            // 11. ESTP
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ESTP")
                            .subtitle("지금 이 순간의 스릴 탐험가")
                            .imageUrl("/image/mbti/ESTP.png")
                            .mainDescription("생각보다 행동, 감정보단 해결이 빠릅니다.\n함께하는 경험 자체를 사랑으로 느낍니다.")
                            .tag1("#액션중심")
                            .tag2("#직설적")
                            .tag3("#즉흥에너지")

                            .basicTendency("ESTP는 호감이 생기면 액션부터 나옵니다. 눈빛 플러팅, 티키타카, 바로 만나는 데이트 제안이 특징이에요. 복잡하게 고민하기보단 함께하는 ‘지금’이 사랑입니다.")
                            .chatStyle("직설적이고 해결 중심입니다. 감정 끌기보단 “그래서 우리 뭐 할까?”로 정리해요.")
                            .loveExpression("같이 놀기, 같이 해결하기, 같이 경험하기가 최고입니다. 말보단 즉각 액션 케미!")
                            .tiredPeople("오랜만에 만나도 바로 어제 본 사람처럼 편합니다. 시간보다 행동의 거리로 유지해요.")

                            .stressReaction("충동적 말과 행동||감정 오래 끌면 짜증↑||해결에 직진||감정은 짧게, 액션은 빠르게")
                            .recoveryMethod("함께하는 액티비티||말보다 행동으로 화해||바로 만나는 데이트||현재 케미")
                            .stressNote("감정은 1줄만, 화해 행동은 즉시! 그 밸런스가 당신의 사랑 언어입니다.")

                            .goodMatch("ISFJ||ISTJ||ESFP")
                            .goodMatchDescription("ESTP는 “지금”과 함께하는 액션이 사랑입니다. ISFJ/ISTJ는 실행 루틴이 탄탄해 ESTP의 빠른 제안과 즉시 데이트가 부담 없이 연결되고, ESFP와는 모멘텀 주고받기 코드가 같아 감정 정리가 빠르고 즐거워요.")
                            .cautionMatch("INFJ||INFP||ENFJ")
                            .cautionMatchDescription("감정의 이유 탐구와 비전 조율 시간을 먼저 쓰고, ESTP는 해결을 먼저 써서 ‘언어 우선순위 충돌’이 생기면 의도 파악에서 오해 빈도가 높습니다.")
                            .build()
            );

            // 12. ISTP
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ISTP")
                            .subtitle("손으로 증명하는 차분한 장인")
                            .imageUrl("/image/mbti/ISTP.png")
                            .mainDescription("감정 대화는 적당히, 실용은 확실히! 행동으로 마음을 표현합니다.\n필요할 때만 손 잡아줍니다.")
                            .tag1("#독립적")
                            .tag2("#문제해결")
                            .tag3("#과정보단결과")

                            .basicTendency("ISTP는 호감이 생기면 해결과 서포트로 나옵니다. 과한 감정 대화보다 상황 해결에 먼저 손을 써요. 말은 적지만 “필요할 땐 항상 옆에” 있는 안정형입니다.")
                            .chatStyle("차분·실용 기반입니다. 감정 충돌은 최소화, 문제 해결은 빠르게!")
                            .loveExpression("도움이 담긴 선물과 결과 중심의 케어입니다. 진심은 과정이 아니라 결과로 증명해요.")
                            .tiredPeople("오래 만나지 않아도 자연스러운 사이를 지향합니다. 손 쓰는 연결 강함.")

                            .stressReaction("감정 충돌 피함 → 혼자 고립||해결 안되면 짜증↑||무표정, 말수 감소||관계가 복잡해지면 거리 조정")
                            .recoveryMethod("혼자 손 쓰는 프로젝트(수리/조립/취미)||문제 해결형 소통||자연이나 조용한 카페||딱 필요한 말만 주고받기")
                            .stressNote("감정이 필요하다고 느낄 때는 “결론 말고 느낌도 한 줄만” 공유해보세요. 부담↓ 친밀↑")

                            .goodMatch("ESTJ||ENTJ||ESFP")
                            .goodMatchDescription("ISTP는 ‘과정보단 해결·결과’로 사랑을 표현해요. ESTJ/ENTJ는 구조 기반 해결 언어라 소통 속도가 맞고, ESFP와는 즉시 함께하는 케미와 행동 공유가 어색함 없이 연결됩니다.")
                            .cautionMatch("ENFP||INFP||ESFJ")
                            .cautionMatchDescription("감정 이유 탐색 언어와, ISTP의 해결 직진 대화 우선순위가 충돌하면 “결론 요구 속도”의 시차로 오해가 생기기 쉬워요.")
                            .build()
            );

            // 13. ESFJ
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ESFJ")
                            .subtitle("다정하고 성실한 케어의 달인")
                            .imageUrl("/image/mbti/ESFJ.png")
                            .mainDescription("관계를 책임감으로 돌보고 소중한 날들을 기억합니다.\n표현과 케어로 사랑을 완성하는 유형입니다.")
                            .tag1("#헌신적")
                            .tag2("#세심한배려")
                            .tag3("#안정추구")

                            .basicTendency("ESFJ는 호감이 생기면 적극적으로 관심과 도움을 건네며 다가갑니다. 기념일, 약속, 취향을 잘 기억하고, 관계의 균형이 흔들리면 예민해질 수 있어요. 솔직한 편이라 마음을 금방 들킬 수도 있습니다.")
                            .chatStyle("정서적 안정 중심의 경청형 대화입니다. 상대의 일상을 세심히 캐치하고, 공감과 반응을 빠르게 건네요.")
                            .loveExpression("애정의 디테일 장인입니다. 깜짝 선물도 좋아하지만, 좋아할 확률 높은 안정형 서프라이즈를 선호해요.")
                            .tiredPeople("가족 같은 온기형 유대가 특징입니다. 직접 챙기고 표현하며 오래가는 신뢰 관계를 지향합니다.")

                            .stressReaction("관계에서 인정과 균형이 흐트러지면 불안이 커짐||감정을 억누르다 누적되면 서운함 폭발||비교와 평가에 예민해짐||주변 사람의 니즈를 과도히 우선시해 번아웃")
                            .recoveryMethod("신뢰하는 사람과 안정감 있는 대화를 통해 서운함 풀기||고마움을 직접 표현하고 돌려받기||익숙한 장소에서 편안한 시간 보내기||직접 도움이 담긴 작은 화해 행동")
                            .stressNote("갈등 상황에서는 해결만 앞세우기보다 먼저 감정의 ‘이유’를 묻고 공감해보세요. 당신의 세심한 기억과 챙김은 관계를 튼튼히 지켜주는 장점이니, 적절한 균형으로 사용하면 더 따뜻한 유대를 만들 수 있어요.")

                            .goodMatch("ISFP||ISTP||ENFJ")
                            .goodMatchDescription("ESFJ는 세심한 케어와 기억력이 강해 ISFP의 감성 니즈를 안정적으로 채워줍니다. ISTP와는 실용적 도움과 관리 방식이 균형을 이루고, ENFJ와는 쌍방 공감 기반으로 사랑과 유대의 온도가 비슷해 조화가 강합니다.")
                            .cautionMatch("INTP||ENTP||INTJ")
                            .cautionMatchDescription("INTP는 감정 요구가 많아지면 거리 조정을 하고, ENTP는 논쟁형 티키타카가 잦아 ESFJ에게 불안으로 느껴질 수 있어요. INTJ는 감정보다는 효율·방향 설계 기반이라 ESFJ의 정서 리액션이 비효율로 해석될 수 있습니다.")
                            .build()
            );

            // 14. ISFJ
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ISFJ")
                            .subtitle("조용히 지키는 따뜻한 온기")
                            .imageUrl("/image/mbti/ISFJ.png")
                            .mainDescription("작은 배려를 행동으로 실천하며 관계를 지킵니다.\n깊은 정과 책임감으로 사랑을 채웁니다.")
                            .tag1("#보호본능")
                            .tag2("#기억의달인")
                            .tag3("#안정적")

                            .basicTendency("ISFJ는 호감이 생기면 직접 표현하기보단 먼저 조용히 챙겨주는 유형입니다. “네가 말했던 거 이거 맞지?” 라며 사소한 취향과 말을 기억하고 실천해요. 호감의 크기가 행동 디테일로 나옵니다.")
                            .chatStyle("감정을 부드럽고 안정적으로 표현합니다. 문제 해결보다 먼저 공감을 건네고, 따뜻하고 편안한 소통 분위기를 만들어요.")
                            .loveExpression("실용적이지만 마음의 온도가 높습니다. 의미 담긴 작은 선물, 직접적인 도움과 케어로 애정을 채워요.")
                            .tiredPeople("오래 만나지 않아도 어색함이 없는 정 기반 관계를 지향합니다. 쌓아둔 신뢰와 기억으로 연결이 자연스럽게 유지됩니다.")

                            .stressReaction("감정을 억누르다 누적 폭발||책임감 과잉으로 번아웃||말보다 행동으로 자책||사람의 변화에 혼자 불안 해석")
                            .recoveryMethod("편안한 안정 기반 1:1 소통||직접 도움이 담긴 행동||익숙하고 아늑한 공간||신뢰 루틴 재정립")
                            .stressNote("먼저 해결하려 들기보다, 이해받고 이해하는 대화에 포커스를 두세요. 당신의 디테일 기억은 큰 애정 무기입니다.")

                            .goodMatch("ESFP||ESTP||ENFJ")
                            .goodMatchDescription("ISFJ는 기억과 행동으로 마음을 지켜요. ESFP/ESTP의 스릴과 모멘텀을 직접 케어로 균형있게 받쳐주고, ENFJ와는 공감 온도와 보호 프레임이 비슷해 안정된 친밀이 지속됩니다.")
                            .cautionMatch("INTP||ENTP||ENTJ")
                            .cautionMatchDescription("INTP는 감정 요구가 커지면 과부하를 느끼고, ENTP/ENTJ는 논쟁·리드 중심이라 ISFJ의 잔잔한 감정 라인 케어가 “결론 회피”로 오해받을 수 있어요.")
                            .build()
            );

            // 15. ESTJ
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ESTJ")
                            .subtitle("확실하고 단단한 현실 설계자")
                            .imageUrl("/image/mbti/ESTJ.png")
                            .mainDescription("감정도 규칙 안에 있어야 안정감을 느낍니다.\n명확하고 계획적인 방식으로 관계를 주도합니다.")
                            .tag1("#책임감")
                            .tag2("#질서중심")
                            .tag3("#리더십")

                            .basicTendency("ESTJ는 호감이 생기면 명확성에 집중해요. 감정 언어보단 행동과 구조 기반으로 다가가고, 답답한 흐름을 싫어합니다. 좋아하면 확실히 표현하지만 효율적으로 전달해요.")
                            .chatStyle("사실 기반으로 경청하고, 감정 과잉보다 해결과 균형에 중심을 둡니다.")
                            .loveExpression("직접 챙겨주는 안정형 서프라이즈, 약속 관리, 책임감으로 표현해요.")
                            .tiredPeople("규칙적 소통, 균형 있는 유대, 오래가는 신뢰를 구조 안에서 느낍니다.")

                            .stressReaction("통제 과잉 → 말이 날카로워짐||감정보단 규칙과 효율로 밀어붙임||비교와 평가에 예민||불안 누적 → 타인 비판으로 나올 수도")
                            .recoveryMethod("구조 안에서 감사 표현 주고받기||해결책을 명백히 정리||책임감 있는 행동으로 안정 찾기||공통 목표 설정")
                            .stressNote("말의 톤이 날카로워지지 않도록 “네 입장에서 보면 어때?” 한 질문을 먼저 건네보세요. 효율도 사랑도 균형이 핵심!")

                            .goodMatch("ISTP||ISFJ||ENTJ")
                            .goodMatchDescription("ESTJ는 계획·균형·책임 기반 언어입니다. ISTP는 해결 직진형이라 갈등이 길어지지 않고, ISFJ는 디테일 기억으로 균형있게 케어해줘 신뢰가 빨리 쌓여요. ENTJ와는 목표 정렬 코드가 같아 관계 운영 속도가 정확히 맞습니다.")
                            .cautionMatch("INFP||ENFP||ISFP")
                            .cautionMatchDescription("감정 동기 중심 언어와 ESTJ의 구조 중심 언어 간 번역 오해가 잦아요. 감정의 이유 탐색 시간이 “비효율”로 읽힐 수 있어 갈등이 누적되기 쉽습니다.")
                            .build()
            );

            // 16. ISTJ
            mbtiRepository.save(
                    Mbti.builder()
                            .type("ISTJ")
                            .subtitle("신뢰와 원칙의 기둥")
                            .imageUrl("/image/mbti/ISTJ.png")
                            .mainDescription("약속과 규칙을 삶과 사랑의 기준으로 삼습니다. 안전하고 예측 가능한 관계에서 편안함을 느낍니다.")
                            .tag1("#논리적")
                            .tag2("#철저한관리")
                            .tag3("#전통적")

                            .basicTendency("ISTJ는 호감이 생기면 말보다 행동으로 다가갑니다. 과한 플러팅보다 신뢰를 쌓는 안정형 접근입니다. 계획이 흔들리거나 즉흥이 많으면 연애에서 피로도를 느낄 수 있어요.")
                            .chatStyle("사실 기반의 신뢰형 대화입니다. 감정 과잉은 줄이고, 실용적이고 차분한 언어로 소통합니다.")
                            .loveExpression("기념일에 실용적 선물, 계획된 데이트, 약속 지키기로 마음을 표현해요. 화려하진 않지만 안정감 100%의 애정 방식입니다.")
                            .tiredPeople("정돈된 루틴 안에서 관계가 오래 지속됩니다. 자주는 아니어도 규칙성이 있는 연결을 선호해요.")

                            .stressReaction("계획 깨지면 빠르게 피로 → 말수 감소||감정을 합리화로 누름||고집↑, 원칙이 흔들리면 짜증↑||변화가 많으면 안정 붕괴")
                            .recoveryMethod("명백한 계획 기반 데이트/약속||정돈, 루틴, 확실한 언어||실용적 도움과 피드백||작은 루틴 서프라이즈 OK")
                            .stressNote("“우리 지금 뭐 하지”보다 “언제, 어디서, 어떻게”를 먼저 정리해보세요. 구조는 당신이 편안해지는 사랑의 형태입니다.")

                            .goodMatch("ESTP||ESFP||ISFJ")
                            .goodMatchDescription("ISTJ는 구조와 신뢰로 사랑을 정의해요. ESTP/ESFP의 현재 케미는 즉시 데이트 행동으로 풀어줘서 소통 오차가 적고, ISFJ의 디테일 케어와 루틴 기반은 ISTJ의 안정 프레임과 정확히 맞물립니다.")
                            .cautionMatch("ENFP||INFP||ENTP")
                            .cautionMatchDescription("감정 동기 중심 언어와 STJ의 사실 중심 언어 간 번역 오류가 잦아요. ENTP의 밀당 토론 충돌이 예고 없이 일어나면 ISTJ에게 관계 안정이 흔들리는 신호로 해석됩니다.")
                            .build()
            );

            System.out.println("✅ MBTI 데이터 16개 초기화 완료! (이미지 포함)");
        }
    }

    // 간단한 데이터 저장 헬퍼 메서드 (상세 내용은 각자 추가)
    private void saveMbti(String type, String subtitle, String imageUrl,
                          String tag1, String tag2, String tag3) {
        mbtiRepository.save(
                Mbti.builder()
                        .type(type)
                        .subtitle(subtitle)
                        .imageUrl(imageUrl)
                        .mainDescription(type + " 타입에 대한 설명입니다.")
                        .tag1(tag1)
                        .tag2(tag2)
                        .tag3(tag3)
                        // 나머지 필드는 기본값 또는 필요에 따라 추가
                        .basicTendency("기본 성향 설명")
                        .chatStyle("대화 스타일1||대화 스타일2")
                        .loveExpression("애정 표현1||애정 표현2")
                        .tiredPeople("피곤한 관계 유형")
                        .stressReaction("스트레스 반응1||스트레스 반응2")
                        .recoveryMethod("회복 방법1||회복 방법2")
                        .stressNote("스트레스 참고사항")
                        .goodMatch("TYPE1||TYPE2||TYPE3")
                        .goodMatchDescription("잘 맞는 이유")
                        .cautionMatch("TYPE4||TYPE5||TYPE6")
                        .cautionMatchDescription("주의가 필요한 이유")
                        .build()
        );
    }
}