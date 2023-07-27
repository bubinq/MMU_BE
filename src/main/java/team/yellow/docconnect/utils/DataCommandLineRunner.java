package team.yellow.docconnect.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.entity.Country;
import team.yellow.docconnect.entity.Doctor;
import team.yellow.docconnect.entity.Specialty;
import team.yellow.docconnect.repository.CityRepository;
import team.yellow.docconnect.repository.CountryRepository;
import team.yellow.docconnect.repository.DoctorRepository;
import team.yellow.docconnect.repository.SpecialtyRepository;

@Component
public class DataCommandLineRunner implements CommandLineRunner {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public DataCommandLineRunner(
            CountryRepository countryRepository,
            CityRepository cityRepository,
            SpecialtyRepository specialtyRepository,
            DoctorRepository doctorRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.specialtyRepository = specialtyRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) {
        Country bulgaria = new Country();
        bulgaria.setName("Bulgaria");
        countryRepository.save(bulgaria);

        City sofia = new City();
        sofia.setName("Sofia");
        sofia.setCountry(bulgaria);
        cityRepository.save(sofia);

        City plovdiv = new City();
        plovdiv.setName("Plovdiv");
        plovdiv.setCountry(bulgaria);
        cityRepository.save(plovdiv);

        City varna = new City();
        varna.setName("Varna");
        varna.setCountry(bulgaria);
        cityRepository.save(varna);

        City burgas = new City();
        burgas.setName("Burgas");
        burgas.setCountry(bulgaria);
        cityRepository.save(burgas);

        City ruse = new City();
        ruse.setName("Ruse");
        ruse.setCountry(bulgaria);
        cityRepository.save(ruse);

        City velikoTarnovo = new City();
        velikoTarnovo.setName("Veliko Tarnovo");
        velikoTarnovo.setCountry(bulgaria);
        cityRepository.save(velikoTarnovo);

        City pleven = new City();
        pleven.setName("Pleven");
        pleven.setCountry(bulgaria);
        cityRepository.save(pleven);

        City staraZagora = new City();
        staraZagora.setName("Stara Zagora");
        staraZagora.setCountry(bulgaria);
        cityRepository.save(staraZagora);

        City dobrich = new City();
        dobrich.setName("Dobrich");
        dobrich.setCountry(bulgaria);
        cityRepository.save(dobrich);

        City sliven = new City();
        sliven.setName("Sliven");
        sliven.setCountry(bulgaria);
        cityRepository.save(sliven);

        Specialty cardiology = new Specialty();
        cardiology.setName("Cardiology");
        cardiology.setImage_url("https://www.cardio.com/hubfs/human%20heart%20illustration.jpeg");
        specialtyRepository.save(cardiology);

        Specialty neurology = new Specialty();
        neurology.setName("Neurology");
        neurology.setImage_url("https://www.cardio.com/hubfs/human%20heart%20illustration.jpeg");
        specialtyRepository.save(neurology);

        Specialty pediatrics = new Specialty();
        pediatrics.setName("Pediatrics");
        pediatrics.setImage_url("https://www.cardio.com/hubfs/human%20heart%20illustration.jpeg");
        specialtyRepository.save(pediatrics);

        Specialty dermatology = new Specialty();
        dermatology.setName("Dermatology");
        dermatology.setImage_url("https://www.cardio.com/hubfs/human%20heart%20illustration.jpeg");
        specialtyRepository.save(dermatology);


        Doctor johnDoe = new Doctor();
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");
        johnDoe.setEmail("john.doe@example.com");
        johnDoe.setSummary("Summary for Doctor John Doe");
        johnDoe.setExperience(10);
        johnDoe.setEducation("Medical School A, Residency B");
        johnDoe.setAverageRating(4.9);
        johnDoe.setImageUrl("https://familydoctor.org/wp-content/uploads/2018/02/41808433_l.jpg");
        johnDoe.setAddress("123 Main Street, New York, USA");
        johnDoe.setCountry(bulgaria);
        johnDoe.setSpecialty(cardiology);
        johnDoe.setCity(varna);
        doctorRepository.save(johnDoe);

        Doctor lisaSmith = new Doctor();
        lisaSmith.setFirstName("Lisa");
        lisaSmith.setLastName("Smith");
        lisaSmith.setEmail("lisa.smith@example.com");
        lisaSmith.setSummary("Summary for Doctor Lisa Smith");
        lisaSmith.setExperience(8);
        lisaSmith.setEducation("Medical School X, Residency Y");
        lisaSmith.setAverageRating(4.8);
        lisaSmith.setImageUrl("https://images.theconversation.com/files/304957/original/file-20191203-66986-im7o5.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1200&h=1200.0&fit=crop");
        lisaSmith.setAddress("456 Oak Street, Berlin, Germany");
        lisaSmith.setCountry(bulgaria);
        lisaSmith.setSpecialty(neurology);
        lisaSmith.setCity(sofia);
        doctorRepository.save(lisaSmith);


        Doctor sarahJohnson = new Doctor();
        sarahJohnson.setFirstName("Sarah");
        sarahJohnson.setLastName("Johnson");
        sarahJohnson.setEmail("sarah.johnson@example.com");
        sarahJohnson.setSummary("Summary for Doctor Sarah Johnson");
        sarahJohnson.setExperience(12);
        sarahJohnson.setEducation("Medical School C, Residency D");
        sarahJohnson.setAverageRating(4.7);
        sarahJohnson.setImageUrl("https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg?w=2000");
        sarahJohnson.setAddress("789 Elm Street, Paris, France");
        sarahJohnson.setCountry(bulgaria);
        sarahJohnson.setSpecialty(pediatrics);
        sarahJohnson.setCity(dobrich);
        doctorRepository.save(sarahJohnson);

        Doctor michaelBrown = new Doctor();
        michaelBrown.setFirstName("Michael");
        michaelBrown.setLastName("Brown");
        michaelBrown.setEmail("michael.brown@example.com");
        michaelBrown.setSummary("Summary for Doctor Michael Brown");
        michaelBrown.setExperience(15);
        michaelBrown.setEducation("Medical School P, Residency Q");
        michaelBrown.setAverageRating(4.9);
        michaelBrown.setImageUrl("https://yt3.googleusercontent.com/ytc/AOPolaQJKYE-RdHOoh0NbbF_dsOPsyVpFbAVDC51sd9xMQ=s900-c-k-c0x00ffffff-no-rj");
        michaelBrown.setAddress("101 Baker Street, London, UK");
        michaelBrown.setCountry(bulgaria);
        michaelBrown.setSpecialty(dermatology);
        michaelBrown.setCity(sliven);
        doctorRepository.save(michaelBrown);


        Doctor ivanGeorgiev = new Doctor();
        ivanGeorgiev.setFirstName("Ivan");
        ivanGeorgiev.setLastName("Georgiev");
        ivanGeorgiev.setEmail("ivan.georgiev@example.com");
        ivanGeorgiev.setSummary("Summary for Doctor Ivan Georgiev");
        ivanGeorgiev.setExperience(9);
        ivanGeorgiev.setEducation("Medical School M, Residency N");
        ivanGeorgiev.setAverageRating(4.6);
        ivanGeorgiev.setImageUrl("https://bdc2020.o0bc.com/wp-content/uploads/2021/05/Elon-Musk-SNL-recap-60978518a78f8-768x432.png");
        ivanGeorgiev.setAddress("15 Ivan Vazov Street, Sofia, Bulgaria");
        ivanGeorgiev.setCountry(bulgaria);
        ivanGeorgiev.setSpecialty(cardiology);
        ivanGeorgiev.setCity(sofia);
        doctorRepository.save(ivanGeorgiev);

        Doctor mariaPetrova = new Doctor();
        mariaPetrova.setFirstName("Maria");
        mariaPetrova.setLastName("Petrova");
        mariaPetrova.setEmail("maria.petrova@example.com");
        mariaPetrova.setSummary("Summary for Doctor Maria Petrova");
        mariaPetrova.setExperience(7);
        mariaPetrova.setEducation("Medical School R, Residency S");
        mariaPetrova.setAverageRating(4.7);
        mariaPetrova.setImageUrl("https://www.onlineshs.com/wp-content/uploads/2022/12/shutterstock_1901822248-1-1.png");
        mariaPetrova.setAddress("10 Hristo Botev Street, Plovdiv, Bulgaria");
        mariaPetrova.setCountry(bulgaria);
        mariaPetrova.setSpecialty(neurology);
        mariaPetrova.setCity(plovdiv);
        doctorRepository.save(mariaPetrova);

        Doctor petarIliev = new Doctor();
        petarIliev.setFirstName("Petar");
        petarIliev.setLastName("Iliev");
        petarIliev.setEmail("petar.iliev@example.com");
        petarIliev.setSummary("Summary for Doctor Petar Iliev");
        petarIliev.setExperience(11);
        petarIliev.setEducation("Medical School T, Residency U");
        petarIliev.setAverageRating(4.9);
        petarIliev.setImageUrl("https://hackensackmeridianhealth.org/-/media/project/hmh/hmh/public/healthu-images/patient-perspectives/20220826-ofer-avi-werthaim-md.png");
        petarIliev.setAddress("5 G.S. Rakovski Street, Varna, Bulgaria");
        petarIliev.setCountry(bulgaria);
        petarIliev.setSpecialty(dermatology);
        petarIliev.setCity(varna);
        doctorRepository.save(petarIliev);
    }
}