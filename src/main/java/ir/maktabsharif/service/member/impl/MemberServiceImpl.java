package ir.maktabsharif.service.member.impl;

import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.exception.InvalidDataException;
import ir.maktabsharif.exception.MemberNotFoundException;
import ir.maktabsharif.model.Member;
import ir.maktabsharif.repository.member.MemberRepo;
import ir.maktabsharif.repository.member.impl.MemberRepoImpl;
import ir.maktabsharif.service.member.MemberService;
import ir.maktabsharif.util.Rule;

import java.time.LocalDate;

public class MemberServiceImpl implements MemberService {
    private final MemberRepo memberRepo = new MemberRepoImpl();

    @Override
    public Member register(Member member) throws BusinessException {
        validate(member);

        memberRepo.insert(member);

        return member;
    }

    @Override
    public Member getById(Long id) throws BusinessException {
        Rule.check(
                id <= 0,
                InvalidDataException::new,
                "ID Must be Positive!"
        );

        return memberRepo.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member Not Found!"));
    }

    @Override
    public Member update(Member member) throws BusinessException {
        Rule.check(
                member.getId() <= 0,
                InvalidDataException::new,
                "ID Must be Positive!"
        );

        validate(member);

        Rule.check(
                !memberRepo.update(member),
                MemberNotFoundException::new,
                "Member Not Found!"
        );

        return member;
    }

    @Override
    public void delete(Long id) throws BusinessException {
        Rule.check(
                id <= 0,
                InvalidDataException::new,
                "ID Must be Positive!"
        );

        Rule.check(
                !memberRepo.delete(id),
                MemberNotFoundException::new,
                "Member Not Found!"
        );
    }

    @Override
    public void validate(Member member) throws BusinessException {
        Rule.check(
                member == null,
                InvalidDataException::new,
                "Member Cannot be Null!"
        );

        Rule.check(
                member.getFullName() == null || member.getFullName().isBlank(),
                InvalidDataException::new,
                "Member's Full Name Cannot be Null or Empty!"
        );

        Rule.check(
                member.getPhone() == null || member.getPhone().isBlank(),
                InvalidDataException::new,
                "Member's Phone Number Cannot be Null or Empty!"
        );

        Rule.check(
                member.getEmail() == null || member.getEmail().isBlank(),
                InvalidDataException::new,
                "Member's Email Cannot be Null or Empty!"
        );

        Rule.check(
                member.getYearOfBirth() < 1700 || member.getYearOfBirth() > LocalDate.now().getYear(),
                InvalidDataException::new,
                "Invalid Member's Year of Birth!"
        );
    }
}
