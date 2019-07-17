package pruebafbi.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebafbi.dtos.DtoFactory;
import pruebafbi.dtos.MemberDto;
import pruebafbi.entities.Member;
import pruebafbi.entities.Status;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.commands.EnableMember;
import pruebafbi.logic.commands.GetSpecialBosses;
import pruebafbi.logic.commands.DisableMember;
import pruebafbi.mappers.MemberMapper;
import pruebafbi.mappers.StatusMapper;

@RestController
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    private GetSpecialBosses getSpecialBosses;
    @Autowired
    private DisableMember disableMember;
    @Autowired
    private EnableMember enableMember;
    @Autowired
    private Gson gson;

    @GetMapping(value = "/bosses")
    private final ResponseEntity getAllSpecialBosses() {
        try {
            getSpecialBosses.initCommand();
            getSpecialBosses.execute();
            return new ResponseEntity(MemberMapper.entityListToDtoList(getSpecialBosses.getResults()), HttpStatus.OK);
        } catch (LogicException le) {
            le.printStackTrace();
            return new ResponseEntity(le.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception le) {
            le.printStackTrace();
            return new ResponseEntity(
                    DtoFactory.instanceCommonResponseDto("UNEXPECTED_ERROR", "FAILURE"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/disable")
    private final ResponseEntity disableMember( @RequestBody String body ) {
        try {
            final MemberDto memberDto = gson.fromJson(body, MemberDto.class);
            final Member member = MemberMapper.dtoToBasicEntity(memberDto);
            final Status status = StatusMapper.dtoToBasicEntity(memberDto.getStatus());
            disableMember.initCommand(member, status);
            disableMember.execute();
            return new ResponseEntity(MemberMapper.entityToDto(disableMember.getResults()), HttpStatus.OK);
        } catch (LogicException le) {
            le.printStackTrace();
            return new ResponseEntity(le.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception le) {
            le.printStackTrace();
            return new ResponseEntity(
                    DtoFactory.instanceCommonResponseDto("UNEXPECTED_ERROR", "FAILURE"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/enable")
    private final ResponseEntity enableMember( @RequestBody String body ) {
        try {
            final MemberDto memberDto = gson.fromJson(body, MemberDto.class);
            final Member member = MemberMapper.dtoToBasicEntity(memberDto);
            enableMember.initCommand(member);
            enableMember.execute();
            return new ResponseEntity(MemberMapper.entityToDto(enableMember.getResults()), HttpStatus.OK);
        } catch (LogicException le) {
            le.printStackTrace();
            return new ResponseEntity(le.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception le) {
            le.printStackTrace();
            return new ResponseEntity(
                    DtoFactory.instanceCommonResponseDto("UNEXPECTED_ERROR", "FAILURE"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}