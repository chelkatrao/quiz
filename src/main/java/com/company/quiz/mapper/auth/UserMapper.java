package com.company.quiz.mapper.auth;

import com.company.quiz.dto.auth.RoleDto;
import com.company.quiz.dto.auth.UserCreateDto;
import com.company.quiz.dto.auth.UserDto;
import com.company.quiz.enums.quiz.*;
import com.company.quiz.model.auth.Role;
import com.company.quiz.model.auth.User;
import com.company.quiz.repository.auth.CompanyRepository;
import com.company.quiz.repository.auth.RoleRepository;
import com.company.quiz.service.UserSession;
import com.google.common.collect.Sets;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private CompanyRepository companyRepository;
    private UserSession userSession;

    public UserMapper(PasswordEncoder passwordEncoder,
                      RoleRepository roleRepository,
                      CompanyRepository companyRepository,
                      UserSession userSession) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
        this.userSession = userSession;
    }

    public User toUser(UserCreateDto userCreateDto) throws Exception {
        return toUser(userCreateDto, new User());
    }

    public User toUser(UserCreateDto userCreateDto, User user) throws Exception {
        if (userCreateDto.getPassword() != null)
            user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setUsername(userCreateDto.getUsername());
        if (userSession.getUser() != null) {
            user.setCreateBy(userSession.getUser().getUsername());
        } else {
            user.setCreateBy("user");
        }
        user.setFullName(userCreateDto.getFullName());
        user.setPhone(userCreateDto.getPhone());
        user.setEmail(userCreateDto.getEmail());
        user.setPathOfCompany(userCreateDto.getPathOfCompany());
        user.setInnovationPartPer(userCreateDto.getInnovationPartPer());
        user.setCompany(companyRepository.findById(userCreateDto.getCompanyId()).orElseGet(null));

        if (userCreateDto.getRoleIds() != null) {
            Set<Role> roles = new HashSet<>();
            for (Long roleId : userCreateDto.getRoleIds()) {
                Role role = roleRepository.findById(roleId).get();
                roles.add(role);
            }
            user.setRoles(roles);
        } else {
            user.setRoles(null);
        }

        if (userCreateDto.getIsEnum()) {
            final int[] i = {0};
            Sets.newHashSet(AnnualActivitiesEnum.values()).stream().forEach(x -> {
                        if (x.getValue().equals(userCreateDto.getAnnualActivitiesEnum())) {
                            i[0]++;
                            user.setAnnualActivitiesEnum(x.getValue());
                        }
                    }
            );
            Sets.newHashSet(HoldersOfAcademicDegreeEnum.values()).stream().forEach(x -> {
                        if (x.getValue().equals(userCreateDto.getHoldersOfAcademicDegreeEnum())) {
                            i[0]++;
                            user.setHoldersOfAcademicDegreeEnum(x.getValue());
                        }
                    }
            );
            Sets.newHashSet(InformationEnum.values()).stream().forEach(x -> {
                        if (x.getValue().equals(userCreateDto.getInformationEnum())) {
                            i[0]++;
                            user.setInformationEnum(x.getValue());
                        }
                    }
            );
            Sets.newHashSet(NationalInnovationEnum.values()).stream().forEach(x -> {
                        if (x.getValue().equals(userCreateDto.getNationalInnovationEnum())) {
                            i[0]++;
                            user.setNationalInnovationEnum(x.getValue());
                        }
                    }
            );
            Sets.newHashSet(NumberOfWorkersEnum.values()).stream().forEach(x -> {
                        if (x.getValue().equals(userCreateDto.getNumberOfWorkersEnum())) {
                            i[0]++;
                            user.setNumberOfWorkersEnum(x.getValue());
                        }
                    }
            );

            Sets.newHashSet(PositionEnum.values()).stream().forEach(x -> {
                        if (x.getValue().equals(userCreateDto.getPositionEnum())) {
                            i[0]++;
                            user.setPositionEnum(x.getValue());
                        }
                    }
            );

            Sets.newHashSet(SexEnum.values()).stream().forEach(x -> {
                        if (x.getValue().equals(userCreateDto.getSexEnum())) {
                            i[0]++;
                            user.setSexEnum(x.getValue());
                        }
                    }
            );

            Sets.newHashSet(TypeOfActivityEnum.values()).stream().forEach(x -> {
                        if (x.getValue().equals(userCreateDto.getTypeOfActivityEnum())) {
                            i[0]++;
                            user.setTypeOfActivityEnum(x.getValue());
                        }
                    }
            );

            Sets.newHashSet(AgeEnum.values()).stream().forEach(x -> {
                        if (x.getValue().equals(userCreateDto.getAge())) {
                            i[0]++;
                            user.setAge(x.getValue());
                        }
                    }
            );
            if (i[0] != 9) {
                throw new Exception("fields are incorrect!!!");
            }

        }

        return user;
    }

    public UserCreateDto toCreateDto(User user) {
        UserCreateDto dto = new UserCreateDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());

        Set<Long> roleIds = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleIds.add(role.getId());
        }

        dto.setRoleIds(roleIds);
        return dto;
    }

    public List<UserDto> listUserToListUserDto(List<User> list) {
        List<UserDto> userListDto = list.stream()
                .map(user ->
                        UserDto.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .roles(user.getRoles().stream()
                                        .map(role -> RoleDto.builder()
                                                .id(role.getId())
                                                .roleName(role.getRoleName())
                                                .roleInfo(role.getRoleInfo())
                                                .build()
                                        ).collect(Collectors.toSet())
                                ).build())
                .collect(Collectors.toList());
        return userListDto;
    }
}
