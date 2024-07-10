package com.groupservice.service;

import com.groupservice.dto.GroupDto;
import com.groupservice.entity.UserGroup;
import com.groupservice.mapper.GroupMapper;
import com.groupservice.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<GroupDto> getAllGroups() {
        List<UserGroup> userGroups = groupRepository.findAll();
        return userGroups.stream().map(GroupMapper.INSTANCE::groupToGroupDto).collect(Collectors.toList());
    }

    public GroupDto getGroupById(Long groupId) {
        UserGroup userGroup = groupRepository.findById(groupId).orElse(null);
        return GroupMapper.INSTANCE.groupToGroupDto(userGroup);
    }

    @Transactional
    public GroupDto createGroup(GroupDto groupDto) {
        UserGroup userGroup = GroupMapper.INSTANCE.groupDtoToGroup(groupDto);
        UserGroup savedUserGroup = groupRepository.save(userGroup);
        return GroupMapper.INSTANCE.groupToGroupDto(savedUserGroup);
    }

    @Transactional
    public GroupDto updateGroup(Long groupId, GroupDto groupDto) {
        UserGroup existingUserGroup = groupRepository.findById(groupId).orElse(null);

        if (existingUserGroup != null) {
            UserGroup newUserGroup = GroupMapper.INSTANCE.groupDtoToGroup(groupDto);
            existingUserGroup.setName(newUserGroup.getName());
            existingUserGroup.setDescription(newUserGroup.getDescription());
            existingUserGroup.setOwnerId(newUserGroup.getOwnerId());
            existingUserGroup.setMemberIds(newUserGroup.getMemberIds());
            existingUserGroup.setProjectIds(newUserGroup.getProjectIds());

            UserGroup updatedUserGroup = groupRepository.save(existingUserGroup);
            return GroupMapper.INSTANCE.groupToGroupDto(updatedUserGroup);
        }
        return null;
    }

    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllGroups() {
        groupRepository.deleteAll();
    }
}


