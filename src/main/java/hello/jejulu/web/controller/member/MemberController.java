package hello.jejulu.web.controller.member;

import hello.jejulu.service.member.MemberService;
import hello.jejulu.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/sign-up")
    public String memberSaveForm(@ModelAttribute MemberDto.Save memberSaveDto){
        return "jejulu/sign/sign-up-member-form";
    }

    @ResponseBody
    @GetMapping("/id-check")
    public boolean memberIdcheck(@RequestParam(name = "v") String checkedId){
        return memberService.isDuplicateId(checkedId);
    }

    @PostMapping
    public String memberSave(@ModelAttribute @Validated MemberDto.Save memberSaveDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info(bindingResult.toString());
            return "jejulu/sign/sign-up-member-form";
        }
        memberService.add(memberSaveDto);
        return "jejulu/success/success-sign-up";
    }
}
