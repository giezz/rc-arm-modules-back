package ru.rightcode.arm.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rightcode.arm.dto.DoctorIdInfo;
import ru.rightcode.arm.dto.request.CreateRehabProgramRequest;
import ru.rightcode.arm.mapper.RehabProgramResponseMapper;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RehabProgramServiceTest {

    @Mock
    private RehabProgramRepository rehabProgramRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private RehabProgramResponseMapper rehabProgramResponseMapper;

    @InjectMocks
    private RehabProgramService rehabProgramService;

    @Test
    void getCurrent_test_throws_EntityNotFoundException_rehab_program() {
        String login = "";
        when(doctorRepository.findByUserUsername(login, DoctorIdInfo.class)).thenReturn(Optional.of(() -> 1L));
        when(rehabProgramRepository.findByDoctorIdAndPatientIdAndIsCurrentTrue(anyLong(), anyLong()))
                .thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> rehabProgramService.getCurrent(login, 1L));
    }

    @Test
    void getCurrent_test_throws_EntityNotFoundException_doctor() {
        String login = "";
        when(doctorRepository.findByUserUsername(login, DoctorIdInfo.class)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> rehabProgramService.getCurrent(login, 1L));
    }

    @Test
    void getCurrent_valid() {
        String login = "";
        long doctorId = 1L;

        when(doctorRepository.findByUserUsername(login, DoctorIdInfo.class)).thenReturn(Optional.of(() -> doctorId));
        when(rehabProgramRepository.findByDoctorIdAndPatientIdAndIsCurrentTrue(doctorId, 1L))
                .thenReturn(Optional.of(new RehabProgram()));

        rehabProgramService.getCurrent(login, doctorId);

        verify(rehabProgramRepository, times(1))
                .findByDoctorIdAndPatientIdAndIsCurrentTrue(doctorId, 1L);
    }

    @Test
    void create_throws_EntityNotFoundException_doctor() {
        when(doctorRepository.findByUserUsername("", DoctorIdInfo.class)).thenReturn(Optional.empty());
        assertThrows(
                EntityNotFoundException.class,
                () -> rehabProgramService.create("", new CreateRehabProgramRequest(1L))
        );
    }

    @Test
    void create_throws_EntityExistsException_rehab_program() {
        when(doctorRepository.findByUserUsername("", DoctorIdInfo.class)).thenReturn(Optional.of(() -> 1L));
        when(rehabProgramRepository.checkIfCurrentExists(anyLong(), anyLong())).thenReturn(true);

        assertThrows(
                EntityExistsException.class,
                () -> rehabProgramService.create("", new CreateRehabProgramRequest(1L)),
                "Программа реабилтации уже существует"
        );
    }

    @Test
    void create_valid() {
        long doctorId = 1L;
        var request = new CreateRehabProgramRequest(1L);
        String login = "";

        when(doctorRepository.findByUserUsername(login, DoctorIdInfo.class)).thenReturn(Optional.of(() -> doctorId));
        when(rehabProgramRepository.checkIfCurrentExists(doctorId, request.patientId())).thenReturn(false);

        ArgumentCaptor<RehabProgram> rehabProgramCaptor = ArgumentCaptor.forClass(RehabProgram.class);
        when(rehabProgramRepository.save(rehabProgramCaptor.capture())).thenReturn(new RehabProgram());

        rehabProgramService.create(login, request);

        assertTrue(rehabProgramCaptor.getValue().getIsCurrent());

        verify(rehabProgramRepository, times(1)).checkIfCurrentExists(doctorId, request.patientId());
        verify(rehabProgramRepository, times(1)).save(any(RehabProgram.class));
    }
}
