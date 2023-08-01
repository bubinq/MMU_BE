package team.yellow.docconnect.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
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
@Profile("dev")
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
        cardiology.setImage_url("https://images.pexels.com/photos/7659564/pexels-photo-7659564.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        specialtyRepository.save(cardiology);

        Specialty orthopedics = new Specialty();
        orthopedics.setName("Orthopedics");
        orthopedics.setImage_url("https://images.pexels.com/photos/7446990/pexels-photo-7446990.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        specialtyRepository.save(orthopedics);

        Specialty neurology = new Specialty();
        neurology.setName("Neurology");
        neurology.setImage_url("https://img.freepik.com/free-photo/doctor-reading-brain-mri-x-ray-result_53876-13389.jpg?w=740&t=st=1677172789~exp=1677173389~hmac=2d0be25306f47a4f0b4aa04d50dac96dd242ad9af01f8b8a9e5ed90385ffaadc");
        specialtyRepository.save(neurology);

        Specialty oncology = new Specialty();
        oncology.setName("Oncology");
        oncology.setImage_url("https://img.freepik.com/free-photo/middle-aged-woman-with-skin-cancer-talking-with-her-doctor_23-2148988517.jpg?w=1380&t=st=1677172867~exp=1677173467~hmac=05adb54dceafc6fce6f602ff87188ba97ebfb7463a91cbf48f7651f7e798d2a5");
        specialtyRepository.save(oncology);

        Specialty gastroenterology = new Specialty();
        gastroenterology.setName("Gastroenterology");
        gastroenterology.setImage_url("https://img.freepik.com/free-photo/this-pain-stomach-is-unbearable_329181-2191.jpg?w=1380&t=st=1677172914~exp=1677173514~hmac=b03857f0631c3fd1b4f4ac82b219a5d68dd98e2eed413f5f7c1ffc57a05c4455");
        specialtyRepository.save(gastroenterology);

        Specialty dermatology = new Specialty();
        dermatology.setName("Dermatology");
        dermatology.setImage_url("https://images.pexels.com/photos/5069432/pexels-photo-5069432.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        specialtyRepository.save(dermatology);

        Specialty pediatrics = new Specialty();
        pediatrics.setName("Pediatrics");
        pediatrics.setImage_url("https://img.freepik.com/free-photo/doctor-doing-their-work-pediatrics-office_23-2149224121.jpg?w=1380&t=st=1677173488~exp=1677174088~hmac=c7742512982c2d4bed8e1b407380e3ae5854843d43dd54716cd6aaf9c11b55aa");
        specialtyRepository.save(pediatrics);

        Specialty urology = new Specialty();
        urology.setName("Urology");
        urology.setImage_url("https://img.freepik.com/free-photo/medical-exam_1098-16897.jpg?w=1380&t=st=1677173522~exp=1677174122~hmac=019e51a1790adec4e1ce26eb0c3fe193fece36e400270fd08a406bb8d5d0023c");
        specialtyRepository.save(urology);

        Specialty ophthalmology = new Specialty();
        ophthalmology.setName("Ophthalmology");
        ophthalmology.setImage_url("https://images.pexels.com/photos/5765827/pexels-photo-5765827.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        specialtyRepository.save(ophthalmology);


        Doctor johnDoe = new Doctor();
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");
        johnDoe.setEmail("john.doe@example.com");
        johnDoe.setSummary("Summary for Doctor John Doe");
        johnDoe.setExperience(10);
        johnDoe.setEducation("Medical School A, Residency B");
        johnDoe.setAverageRating(4.9);
        johnDoe.setImageUrl("https://familydoctor.org/wp-content/uploads/2018/02/41808433_l.jpg");
        johnDoe.setAddress("123 Main Street, Varna, Bulgaria");
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
        lisaSmith.setImageUrl("https://t3.ftcdn.net/jpg/01/30/45/54/360_F_130455409_fTuinPO1LXECv5hlk9VBREnL6yowYUo3.jpg");
        lisaSmith.setAddress("456 Oak Street, Sofia, Bulgaria");
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
        sarahJohnson.setImageUrl("https://cdn.create.vista.com/api/media/small/80150956/stock-photo-confident-female-doctor-at-office");
        sarahJohnson.setAddress("789 Elm Street, Dobrich, Bulgaria");
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
        michaelBrown.setImageUrl("https://media.istockphoto.com/id/1385197689/photo/portrait-of-cheerful-handsome-black-doctor-posing-at-clinic.webp?b=1&s=170667a&w=0&k=20&c=aAVV7sLkOzWb4tv1z5wy-UWCcSTt4ALZW2Q6V1UqNP4=");
        michaelBrown.setAddress("101 Baker Street, Sliven, Bulgaria");
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

        Doctor stanleyRivera = new Doctor();
        stanleyRivera.setFirstName("Stanley");
        stanleyRivera.setLastName("Rivera");
        stanleyRivera.setEmail("stanley.rivera@example.com");
        stanleyRivera.setSummary("Summary for Doctor Stanley Rivera");
        stanleyRivera.setExperience(21);
        stanleyRivera.setEducation("Medical School SS, Residency AA");
        stanleyRivera.setAverageRating(4.2);
        stanleyRivera.setImageUrl("https://img.freepik.com/free-photo/smiling-doctor-with-strethoscope-isolated-grey_651396-974.jpg?size=626&ext=jpg");
        stanleyRivera.setAddress("Bul. Andrej Lyapchev 32, Sofia, Bulgaria");
        stanleyRivera.setCountry(bulgaria);
        stanleyRivera.setSpecialty(ophthalmology);
        stanleyRivera.setCity(sofia);
        doctorRepository.save(stanleyRivera);

        Doctor jolieRose = new Doctor();
        jolieRose.setFirstName("Jolie");
        jolieRose.setLastName("Rose");
        jolieRose.setEmail("jolie.rose@example.com");
        jolieRose.setSummary("Summary for Doctor Jolie Rose");
        jolieRose.setExperience(14);
        jolieRose.setEducation("Medical School H, Residency P");
        jolieRose.setAverageRating(4.4);
        jolieRose.setImageUrl("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg?size=626&ext=jpg");
        jolieRose.setAddress("6 Mihail Sarafov Street, Sofia, Bulgaria");
        jolieRose.setCountry(bulgaria);
        jolieRose.setSpecialty(urology);
        jolieRose.setCity(sofia);
        doctorRepository.save(jolieRose);

        Doctor loganFlynn = new Doctor();
        loganFlynn.setFirstName("Logan");
        loganFlynn.setLastName("Flynn");
        loganFlynn.setEmail("logan.flynn@example.com");
        loganFlynn.setSummary("Summary for Doctor Logan Flynn");
        loganFlynn.setExperience(14);
        loganFlynn.setEducation("Medical School Q, Residency Z");
        loganFlynn.setAverageRating(5);
        loganFlynn.setImageUrl("https://img.freepik.com/free-photo/doctor-with-his-arms-crossed-white-background_1368-5789.jpg?size=626&ext=jpg");
        loganFlynn.setAddress("14 Dickson Street, Veliko Tarnovo, Bulgaria");
        loganFlynn.setCountry(bulgaria);
        loganFlynn.setSpecialty(oncology);
        loganFlynn.setCity(velikoTarnovo);
        doctorRepository.save(loganFlynn);

        Doctor skylaHodge = new Doctor();
        skylaHodge.setFirstName("Skyla");
        skylaHodge.setLastName("Hodge");
        skylaHodge.setEmail("skyla.hodge@example.com");
        skylaHodge.setSummary("Summary for Doctor Skyla Hodge");
        skylaHodge.setExperience(4);
        skylaHodge.setEducation("Medical School D, Residency L");
        skylaHodge.setAverageRating(3.8);
        skylaHodge.setImageUrl("https://img.freepik.com/free-photo/portrait-smiling-medical-worker-girl-doctor-white-coat-with-stethoscope-pointing-fingers-left-showing-medical-clinic-advertisement-torquoise-background_1258-87675.jpg?size=626&ext=jpg");
        skylaHodge.setAddress("1 Lombard Street, Ruse, Bulgaria");
        skylaHodge.setCountry(bulgaria);
        skylaHodge.setSpecialty(orthopedics);
        skylaHodge.setCity(ruse);
        doctorRepository.save(skylaHodge);

        Doctor rebeccaPeters = new Doctor();
        rebeccaPeters.setFirstName("Rebecca");
        rebeccaPeters.setLastName("Peters");
        rebeccaPeters.setEmail("rebecca.peters@example.com");
        rebeccaPeters.setSummary("Summary for Doctor Rebecca Peters");
        rebeccaPeters.setExperience(16);
        rebeccaPeters.setEducation("Medical School Y, Residency U");
        rebeccaPeters.setAverageRating(4.8);
        rebeccaPeters.setImageUrl("https://media.istockphoto.com/id/1189304032/photo/doctor-holding-digital-tablet-at-meeting-room.jpg?s=612x612&w=0&k=20&c=RtQn8w_vhzGYbflSa1B5ea9Ji70O8wHpSgGBSh0anUg=");
        rebeccaPeters.setAddress("36 Angel Voyvoda Street, Stara Zagora, Bulgaria");
        rebeccaPeters.setCountry(bulgaria);
        rebeccaPeters.setSpecialty(gastroenterology);
        rebeccaPeters.setCity(staraZagora);
        doctorRepository.save(rebeccaPeters);

        Doctor alanPollard = new Doctor();
        alanPollard.setFirstName("Alan");
        alanPollard.setLastName("Pollard");
        alanPollard.setEmail("alan.pollard@example.com");
        alanPollard.setSummary("Summary for Doctor Alan Pollard");
        alanPollard.setExperience(26);
        alanPollard.setEducation("Medical School J, Residency B");
        alanPollard.setAverageRating(4.6);
        alanPollard.setImageUrl("https://img.freepik.com/free-photo/attractive-young-male-nutriologist-lab-coat-smiling-against-white-background_662251-2960.jpg");
        alanPollard.setAddress("2 Angista Street, Dobrich, Bulgaria");
        alanPollard.setCountry(bulgaria);
        alanPollard.setSpecialty(pediatrics);
        alanPollard.setCity(dobrich);
        doctorRepository.save(alanPollard);

        Doctor borisKamenov = new Doctor();
        borisKamenov.setFirstName("Boris");
        borisKamenov.setLastName("Kamenov");
        borisKamenov.setEmail("boris.kamenov@example.com");
        borisKamenov.setSummary("Summary for Doctor Boris Kamenov");
        borisKamenov.setExperience(6);
        borisKamenov.setEducation("Medical School L, Residency V");
        borisKamenov.setAverageRating(4);
        borisKamenov.setImageUrl("https://images.wsj.net/im-43344/8SR");
        borisKamenov.setAddress("24 Oven Street, Plovdiv, Bulgaria");
        borisKamenov.setCountry(bulgaria);
        borisKamenov.setSpecialty(ophthalmology);
        borisKamenov.setCity(plovdiv);
        doctorRepository.save(borisKamenov);

        Doctor sheiLu = new Doctor();
        sheiLu.setFirstName("Shei");
        sheiLu.setLastName("Lu");
        sheiLu.setEmail("shei.lu@example.com");
        sheiLu.setSummary("Summary for Doctor Shei Lu");
        sheiLu.setExperience(17);
        sheiLu.setEducation("Medical School Z, Residency X");
        sheiLu.setAverageRating(4.2);
        sheiLu.setImageUrl("https://www.uclahealth.org/sites/default/files/styles/portrait_3x4_016000_480x640/public/images/female-doc-with-other-docs.jpg?h=dd028d5a&itok=ajLNibn5");
        sheiLu.setAddress("8 Kapitan Andreev Street, Sliven, Bulgaria");
        sheiLu.setCountry(bulgaria);
        sheiLu.setSpecialty(dermatology);
        sheiLu.setCity(sliven);
        doctorRepository.save(sheiLu);

//        Doctor johnnySins = new Doctor();
//        johnnySins.setFirstName("Johnny");
//        johnnySins.setLastName("Sins");
//        johnnySins.setEmail("johnny.sins@example.com");
//        johnnySins.setSummary("Summary for Doctor Johny Sins");
//        johnnySins.setExperience(100);
//        johnnySins.setEducation("Medical School HH, Residency OP");
//        johnnySins.setAverageRating(5);
//        johnnySins.setImageUrl("https://wallpapers.com/images/high/doctor-johnny-sins-6vleyqyzdu5tcn5c.webp");
//        johnnySins.setAddress("8 Great Street, Sofia, Bulgaria");
//        johnnySins.setCountry(bulgaria);
//        johnnySins.setSpecialty(urology);
//        johnnySins.setCity(sofia);
//        doctorRepository.save(johnnySins);
    }
}