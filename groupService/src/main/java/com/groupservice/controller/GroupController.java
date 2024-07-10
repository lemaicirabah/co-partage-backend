package com.groupservice.controller;

import com.groupservice.dto.GroupDto;
import com.groupservice.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/co-partage/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        GroupDto createdGroup = groupService.createGroup(groupDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long id) {
        GroupDto group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        groupDto.setId(id); // Ensure the ID is set for update
        GroupDto updatedGroup = groupService.updateGroup(id, groupDto);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}
