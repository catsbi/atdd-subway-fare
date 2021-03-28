package nextstep.subway.path.domain.policy;

import nextstep.subway.path.dto.CostRequest;

public class AddedCostPaymentPolicy implements PaymentPolicy {
    private PaymentPolicy next;

    @Override
    public CostRequest cost(CostRequest costRequest) {
        long maximumAddedCost = costRequest.getPathResult().getMaximumAddedCost();
        costRequest.addCost(maximumAddedCost);
        return costRequest;
    }
}
