package hi.place.controller;

import hi.place.dto.user.UserRequestDto;
import hi.place.dto.user.UserResponseDto;
import hi.place.model.user.User;
import hi.place.service.UserService;
import hi.place.service.mapper.RequestResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RequestResponseMapper<UserRequestDto, UserResponseDto, User> userMapper;

    @GetMapping("/email")
    public ResponseEntity<UserResponseDto> getByEmail(Authentication authentication) {
        return new ResponseEntity<>(userMapper.toDto(userService.getByEmail(authentication.getName())),
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/type-of-service/city/{city}")
    public ResponseEntity<Page<UserResponseDto>> getAllByTypeOfService(@RequestParam Long serviceTypeId,
                                                                       @PathVariable String city,
                                                                       @RequestParam(defaultValue = "MASTER") String sortByType,
                                                                       @RequestParam(defaultValue = "false") Boolean sortByAtSalon,
                                                                       @RequestParam(defaultValue = "false") Boolean sortByHomeVisit,
                                                                       @RequestParam(defaultValue = "false") Boolean sortByOnlineCounseling,
                                                                       Pageable pageable) {

        Page<User> userPage = userService.getAllByTypeOfServiceId(serviceTypeId,
                city,
                sortByType,
                sortByAtSalon,
                sortByHomeVisit,
                sortByOnlineCounseling,
                pageable);

        return new ResponseEntity<>(userPage.map(userMapper::toDto), HttpStatus.OK);
    }

    @GetMapping("/main-service/city/{city}")
    public ResponseEntity<Page<UserResponseDto>> getAllByMainTypeOfService(@RequestParam Long mainTypeOfServiceId,
                                                                           @PathVariable String city,
                                                                           @RequestParam(defaultValue = "MASTER") String sortByType,
                                                                           @RequestParam(defaultValue = "false") Boolean sortByAtSalon,
                                                                           @RequestParam(defaultValue = "false") Boolean sortByHomeVisit,
                                                                           @RequestParam(defaultValue = "false") Boolean sortByOnlineCounseling,
                                                                           Pageable pageable) {
        Page<User> userPage = userService.getAllByMainTypeOfServiceId(mainTypeOfServiceId,
                city,
                sortByType,
                sortByAtSalon,
                sortByHomeVisit,
                sortByOnlineCounseling,
                pageable);

        return new ResponseEntity<>(userPage.map(userMapper::toDto), HttpStatus.OK);
    }

    @GetMapping("/service-item/city/{city}")
    public ResponseEntity<Page<UserResponseDto>> getAllByServiceItem(@RequestParam Long serviceItemId,
                                                                     @PathVariable String city,
                                                                     @RequestParam(defaultValue = "MASTER") String sortByType,
                                                                     @RequestParam(defaultValue = "false") Boolean sortByAtSalon,
                                                                     @RequestParam(defaultValue = "false") Boolean sortByHomeVisit,
                                                                     @RequestParam(defaultValue = "false") Boolean sortByOnlineCounseling,
                                                                     Pageable pageable) {
        Page<User> userPage = userService.getAllByServiceItemId(serviceItemId,
                city,
                sortByType,
                sortByAtSalon,
                sortByHomeVisit,
                sortByOnlineCounseling,
                pageable);

        return new ResponseEntity<>(userPage.map(userMapper::toDto), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userMapper.toDto(userService.getById(userId)), HttpStatus.OK);
    }
}
