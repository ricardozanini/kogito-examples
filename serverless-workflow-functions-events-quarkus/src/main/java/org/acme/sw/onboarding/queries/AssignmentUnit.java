package org.acme.sw.onboarding.queries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.acme.sw.onboarding.model.Doctor;
import org.acme.sw.onboarding.model.Patient;
import org.acme.sw.onboarding.model.SymptomSpecialty;
import org.acme.sw.onboarding.services.DoctorService;
import org.acme.sw.onboarding.services.SymptomSpecialtyService;
import org.kie.kogito.rules.DataSource;
import org.kie.kogito.rules.DataStore;
import org.kie.kogito.rules.RuleUnitData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignmentUnit implements RuleUnitData {

    private DataStore<Patient> patients;
    private DataStore<Doctor> doctors;
    private DataStore<SymptomSpecialty> symptomSpecialties;

    public AssignmentUnit() {
        this.patients = DataSource.createStore();
        this.doctors = DataSource.createStore();
        this.symptomSpecialties = DataSource.createStore();
        this.populate();
    }

    private void populate() {
        DoctorService.get().getDoctors().forEach(this.doctors::add);
        SymptomSpecialtyService.get().getSymptomSpecialties().forEach(this.symptomSpecialties::add);
    }

    public DataStore<Patient> getPatients() {
        return patients;
    }

    public void setPatients(DataStore<Patient> patients) {
        this.patients = patients;
    }

    @JsonIgnore
    public DataStore<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(DataStore<Doctor> doctors) {
        this.doctors = doctors;
    }

    @JsonIgnore
    public DataStore<SymptomSpecialty> getSymptomSpecialties() {
        return symptomSpecialties;
    }

    public void setSymptomSpecialties(DataStore<SymptomSpecialty> symptomSpecialties) {
        this.symptomSpecialties = symptomSpecialties;
    }
}
