package finki.ukim.mk.hospital_managment_system.repository.inmemory;

import finki.ukim.mk.hospital_managment_system.StaticData.DataHolder;
import finki.ukim.mk.hospital_managment_system.model.Term;
import finki.ukim.mk.hospital_managment_system.repository.TermRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryTermRepositoryImpl implements TermRepository {

    @Override
    public Optional<Term> findById(String termId) {
        return DataHolder.termList.stream()
                .filter(term -> term.getId().equals(termId))
                .findFirst();
    }

    @Override
    public Term save(Term term) {
        findById(term.getId()).ifPresent(term1 -> DataHolder.termList.remove(term1));
        DataHolder.termList.add(term);
        return term;
    }

    @Override
    public void deleteById(String termId) {
        findById(termId).ifPresent(term -> DataHolder.termList.remove(term));
    }

    @Override
    public List<Term> searchByDoctorIdAndStatus(String doctorId, String status) {
        return DataHolder.termList.stream()
                .filter(term -> term.getDoctor().getId().equals(doctorId) && term.getStatus().equals(status))
                .collect(Collectors.toList());

    }
}
