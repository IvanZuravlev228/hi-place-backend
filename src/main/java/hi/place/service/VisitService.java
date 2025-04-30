package hi.place.service;

import hi.place.model.user.User;

public interface VisitService {
    void logVisit(String ipAddress, Long userId);

    Integer getVisitCount(Long userId, Long startTime, Long endTime);
}
