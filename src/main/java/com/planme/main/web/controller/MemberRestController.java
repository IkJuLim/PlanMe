package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.MemberConverter;
import com.planme.main.converter.TermConverter;
import com.planme.main.domain.Member;
import com.planme.main.domain.mapping.TermsAgreement;
import com.planme.main.service.memberService.MemberService;
import com.planme.main.service.termService.TermService;
import com.planme.main.service.termService.TermServiceImpl;
import com.planme.main.web.dto.MemberDTO.MemberDTO;
import com.planme.main.web.dto.TermDTO.TermRequestDTO;
import com.planme.main.web.dto.TermDTO.TermResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberRestController {
    private final MemberService memberService;
    private final MemberConverter memberConverter;
    private final TermService termService;
    @GetMapping
    public ApiResponse<MemberDTO> getMember(HttpServletRequest httpServletRequest){
        Member member = memberService.getMember(httpServletRequest);
        return ApiResponse.of(SuccessStatus.MEMBER_FOUND,memberConverter.toMemberDTO(member));
    }

    @PostMapping("/terms")
    public ApiResponse<TermResponseDTO.TermAgreeResultDTO> termAgree(@RequestBody TermRequestDTO.TermAgreeDTO termAgreeDTO){
        List<TermsAgreement> agree = termService.agree(termAgreeDTO);
        return ApiResponse.of(SuccessStatus.MEMBER_TERMS_AGREED, TermConverter.toTermAgreeResultDTO(agree));
    }
}

