package com.company.quiz.controller.auth;

import com.company.quiz.dto.auth.UserCreateDto;
import com.company.quiz.dto.auth.UserDto;
import com.company.quiz.dto.quiz.CompanyDto;
import com.company.quiz.model.quiz.Company;
import com.company.quiz.repository.auth.CompanyRepository;
import com.company.quiz.service.auth.RoleService;
import com.company.quiz.service.auth.UserService;
import com.company.quiz.service.quiz.CompanyService;
import com.google.common.collect.Sets;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private CompanyService companyService;
    private CompanyRepository companyRepository;

    public UserController(UserService userService,
                          RoleService roleService,
                          CompanyRepository companyRepository,
                          CompanyService companyService) {
        this.userService = userService;
        this.roleService = roleService;
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/list-full")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public List<UserDto> getUserListFull() {
        return userService.getUserListFull();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public List<UserDto> getUserList() {
        return userService.getUserList();
    }

    @PostMapping("/new")
    public Object createUser(@RequestBody UserCreateDto userCreateDto) throws Exception {
//        Boolean company = companyService.findByCompanyId(userCreateDto.getCompanyId(), userCreateDto.getCompanyCode());
//        Boolean isExist = companyService.findByCode(userCreateDto.getCompanyCode());
//        if (isExist) {

        try {
            userCreateDto.setRoleIds(Sets.newHashSet(roleService.getRoleByName("USER_ROLE").getId()));

            Company company = companyRepository.findByCompanyName(userCreateDto.getCompanyName());
            if (company == null) {
                CompanyDto companyDto = new CompanyDto();
                companyDto.setCode("00x");
                companyDto.setCompanyName(userCreateDto.getCompanyName());
                company = companyService.createCompany(companyDto);
            }
            userCreateDto.setCompanyId(company.getId());
            userService.createUser(userCreateDto);
            return company.getId();
        } catch (Exception e) {
            return "error";
        }
//        } else {
//            return "error";
//        }
    }

    @GetMapping("/remove/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public Boolean removeUser(@PathVariable("id") Long id) throws Exception {
        return userService.removeUserById(id);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public UserCreateDto updateUser(@RequestBody UserCreateDto userUpdateDto,
                                    @PathVariable("id") Long id) throws Throwable {
        return userService.updateUser(userUpdateDto, id);
    }
}
