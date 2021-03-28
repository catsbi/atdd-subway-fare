package nextstep.subway.path.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DistancePaymentPolicy 클래스")
public class DistancePaymentPolicyTest extends IsolatePathTest {
    private PaymentPolicy paymentPolicy = new DistancePaymentPolicy();

    @DisplayName("cost 메서드는")
    @Nested
    class Describe_cost{
        @Nested
        @DisplayName("경로의 총 거리가 10km 이내일 경우")
        class Context_with_distance_under_10 {
            @DisplayName("기본요금이 부과된다.")
            @Test
            void it_is_default_cost() {
                //when
                Cost cost = paymentPolicy.cost(강남역_양재역_경로);

                //then
                assertThat(cost.getCost()).isEqualTo(PaymentPolicy.DEFAULT_COST + 900);
            }
        }

        @Nested
        @DisplayName("경로의 총 거리가 10km 초과 50km 이하일 경우")
        class Context_with_distance_between_10_and_50 {
            @DisplayName("5키로당 100원의 요금이 부과된다.")
            @Test
            void it_is_additional_cost_100_by_5km() {
                //when
                Cost cost = paymentPolicy.cost(교대역_남부터미널역_경로);

                //then
                assertThat(cost.getCost()).isEqualTo(1950);
            }
        }

        @Nested
        @DisplayName("경로의 총 거리가 50km 초과일 경우")
        class Context_with_distance_exceed_50 {
            @DisplayName("8키로당 100원씩 요금이 부과된다.")
            @Test
            void it_is_additional_cost_100_by_8km() {
                //when
                Cost cost = paymentPolicy.cost(양재역_교대역_경로);

                //then 59km 1250 + 800 + 200
                assertThat(cost.getCost()).isEqualTo(2750);
            }
        }

    }

}
