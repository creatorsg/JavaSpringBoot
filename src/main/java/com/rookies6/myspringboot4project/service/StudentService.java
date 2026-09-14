package com.rookies6.myspringboot4project.service;

import com.rookies6.myspringboot4project.entity.Student;
import com.rookies6.myspringboot4project.entity.StudentDetail;
import com.rookies6.myspringboot4project.exception.DuplicateStudentException;
import com.rookies6.myspringboot4project.exception.StudentNotFoundException;
import com.rookies6.myspringboot4project.repository.StudentDetailRepository;
import com.rookies6.myspringboot4project.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentDetailRepository studentDetailRepository;

    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream()
                .map(StudentResponse::from)
                .toList();
    }

    public StudentResponse findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return StudentResponse.from(student);
    }

    @Transactional
    public void delete(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
    }

    @Transactional
    public StudentResponse update(Long id, StudentCreateRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        StudentDetailRequest detailReq = request.getDetailRequest();
        validateDuplicateForUpdate(student, request.getStudentNumber(), detailReq.getEmail(), detailReq.getPhoneNumber());

        student.setName(request.getName());
        student.setStudentNumber(request.getStudentNumber());

        StudentDetail detail = student.getStudentDetail();
        detail.setAddress(detailReq.getAddress());
        detail.setPhoneNumber(detailReq.getPhoneNumber());
        detail.setEmail(detailReq.getEmail());
        detail.setDateOfBirth(detailReq.getDateOfBirth());

        return StudentResponse.from(student);
    }

    @Transactional
    public StudentResponse create(StudentCreateRequest request) {
        StudentDetailRequest detailReq = request.getDetailRequest();
        validateDuplicate(request.getStudentNumber(), detailReq.getEmail(), detailReq.getPhoneNumber());

        StudentDetail detail = StudentDetail.builder()
                .address(detailReq.getAddress())
                .phoneNumber(detailReq.getPhoneNumber())
                .email(detailReq.getEmail())
                .dateOfBirth(detailReq.getDateOfBirth())
                .build();

        Student student = Student.builder()
                .name(request.getName())
                .studentNumber(request.getStudentNumber())
                .build();

        student.setStudentDetail(detail);
        detail.setStudent(student);

        Student saved = studentRepository.save(student); // cascade ALL → detail도 같이 INSERT
        return StudentResponse.from(saved);
    }

    private void validateDuplicate(String studentNumber, String email, String phoneNumber) {
        if (studentRepository.existsByStudentNumber(studentNumber)) {
            throw new DuplicateStudentException("이미 등록된 학번입니다.");
        }
        if (studentDetailRepository.existsByEmail(email)) {
            throw new DuplicateStudentException("이미 등록된 이메일입니다.");
        }
        if (studentDetailRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicateStudentException("이미 등록된 전화번호입니다.");
        }
    }

    private void validateDuplicateForUpdate(Student student, String newStudentNumber, String newEmail, String newPhoneNumber) {
        if (!student.getStudentNumber().equals(newStudentNumber)
                && studentRepository.existsByStudentNumber(newStudentNumber)) {
            throw new DuplicateStudentException("이미 등록된 학번입니다.");
        }

        StudentDetail detail = student.getStudentDetail();
        if (!detail.getEmail().equals(newEmail)
                && studentDetailRepository.existsByEmail(newEmail)) {
            throw new DuplicateStudentException("이미 등록된 이메일입니다.");
        }
        if (!detail.getPhoneNumber().equals(newPhoneNumber)
                && studentDetailRepository.existsByPhoneNumber(newPhoneNumber)) {
            throw new DuplicateStudentException("이미 등록된 전화번호입니다.");
        }
    }
}