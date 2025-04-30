package hi.place.filter;

import hi.place.service.VisitService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class VisitUserProfileFilter extends OncePerRequestFilter {
    private static final String TRACKED_ENDPOINT = "/type-service/user/";

    private final VisitService visitService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().startsWith(TRACKED_ENDPOINT)) {
            String ipAddress = request.getRemoteAddr();
            String[] uriParts = request.getRequestURI().split("/");
            try {
                Long userId = Long.parseLong(uriParts[uriParts.length - 1]);
                visitService.logVisit(ipAddress, userId);
            } catch (NumberFormatException e) {
                System.err.println("Некорректный параметр userId: " + uriParts[uriParts.length - 1]);
            }
        }

        filterChain.doFilter(request, response);
    }
}
