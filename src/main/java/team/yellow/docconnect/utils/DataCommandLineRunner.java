package team.yellow.docconnect.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.entity.State;
import team.yellow.docconnect.entity.Doctor;
import team.yellow.docconnect.entity.Specialty;
import team.yellow.docconnect.repository.CityRepository;
import team.yellow.docconnect.repository.StateRepository;
import team.yellow.docconnect.repository.DoctorRepository;
import team.yellow.docconnect.repository.SpecialtyRepository;

@Component
@Profile("dev")
public class DataCommandLineRunner implements CommandLineRunner {

    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public DataCommandLineRunner(
            StateRepository stateRepository,
            CityRepository cityRepository,
            SpecialtyRepository specialtyRepository,
            DoctorRepository doctorRepository) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.specialtyRepository = specialtyRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) {
        State alabama = new State();
        alabama.setName("Alabama");
        stateRepository.save(alabama);

        State alaska = new State();
        alaska.setName("Alaska");
        stateRepository.save(alaska);

        State arizona = new State();
        arizona.setName("Arizona");
        stateRepository.save(arizona);

        State arkansas = new State();
        arkansas.setName("Arkansas");
        stateRepository.save(arkansas);

        State california = new State();
        california.setName("California");
        stateRepository.save(california);

        State colorado = new State();
        colorado.setName("Colorado");
        stateRepository.save(colorado);

        State connecticut = new State();
        connecticut.setName("Connecticut");
        stateRepository.save(connecticut);

        State delaware = new State();
        delaware.setName("Delaware");
        stateRepository.save(delaware);

        State florida = new State();
        florida.setName("Florida");
        stateRepository.save(florida);

        State georgia = new State();
        georgia.setName("Georgia");
        stateRepository.save(georgia);

        State hawaii = new State();
        hawaii.setName("Hawaii");
        stateRepository.save(hawaii);

        State idaho = new State();
        idaho.setName("Idaho");
        stateRepository.save(idaho);

        State illinois = new State();
        illinois.setName("Illinois");
        stateRepository.save(illinois);

        State indiana = new State();
        indiana.setName("Indiana");
        stateRepository.save(indiana);

        State iowa = new State();
        iowa.setName("Iowa");
        stateRepository.save(iowa);

        State kansas = new State();
        kansas.setName("Kansas");
        stateRepository.save(kansas);

        State kentucky = new State();
        kentucky.setName("Kentucky");
        stateRepository.save(kentucky);

        State louisiana = new State();
        louisiana.setName("Louisiana");
        stateRepository.save(louisiana);

        State maine = new State();
        maine.setName("Maine");
        stateRepository.save(maine);

        State maryland = new State();
        maryland.setName("Maryland");
        stateRepository.save(maryland);

        State massachusetts = new State();
        massachusetts.setName("Massachusetts");
        stateRepository.save(massachusetts);

        State michigan = new State();
        michigan.setName("Michigan");
        stateRepository.save(michigan);

        State minnesota = new State();
        minnesota.setName("Minnesota");
        stateRepository.save(minnesota);

        State mississippi = new State();
        mississippi.setName("Mississippi");
        stateRepository.save(mississippi);

        State missouri = new State();
        missouri.setName("Missouri");
        stateRepository.save(missouri);

        State montana = new State();
        montana.setName("Montana");
        stateRepository.save(montana);

        State nebraska = new State();
        nebraska.setName("Nebraska");
        stateRepository.save(nebraska);

        State nevada = new State();
        nevada.setName("Nevada");
        stateRepository.save(nevada);

        State newHampshire = new State();
        newHampshire.setName("New Hampshire");
        stateRepository.save(newHampshire);

        State newJersey = new State();
        newJersey.setName("New Jersey");
        stateRepository.save(newJersey);

        State newMexico = new State();
        newMexico.setName("New Mexico");
        stateRepository.save(newMexico);

        State newYork = new State();
        newYork.setName("New York");
        stateRepository.save(newYork);

        State northCarolina = new State();
        northCarolina.setName("North Carolina");
        stateRepository.save(northCarolina);

        State northDakota = new State();
        northDakota.setName("North Dakota");
        stateRepository.save(northDakota);

        State ohio = new State();
        ohio.setName("Ohio");
        stateRepository.save(ohio);

        State oklahoma = new State();
        oklahoma.setName("Oklahoma");
        stateRepository.save(oklahoma);

        State oregon = new State();
        oregon.setName("Oregon");
        stateRepository.save(oregon);

        State pennsylvania = new State();
        pennsylvania.setName("Pennsylvania");
        stateRepository.save(pennsylvania);

        State rhodeIsland = new State();
        rhodeIsland.setName("Rhode Island");
        stateRepository.save(rhodeIsland);

        State southCarolina = new State();
        southCarolina.setName("South Carolina");
        stateRepository.save(southCarolina);

        State southDakota = new State();
        southDakota.setName("South Dakota");
        stateRepository.save(southDakota);

        State tennessee = new State();
        tennessee.setName("Tennessee");
        stateRepository.save(tennessee);

        State texas = new State();
        texas.setName("Texas");
        stateRepository.save(texas);

        State utah = new State();
        utah.setName("Utah");
        stateRepository.save(utah);

        State vermont = new State();
        vermont.setName("Vermont");
        stateRepository.save(vermont);

        State virginia = new State();
        virginia.setName("Virginia");
        stateRepository.save(virginia);

        State washington = new State();
        washington.setName("Washington");
        stateRepository.save(washington);

        State westVirginia = new State();
        westVirginia.setName("West Virginia");
        stateRepository.save(westVirginia);

        State wisconsin = new State();
        wisconsin.setName("Wisconsin");
        stateRepository.save(wisconsin);

        State wyoming = new State();
        wyoming.setName("Wyoming");
        stateRepository.save(wyoming);


//        // Alabama
//        City birmingham = new City();
//        birmingham.setName("Birmingham");
//        birmingham.setState(alabama);
//        cityRepository.save(birmingham);
//
//        City montgomery = new City();
//        montgomery.setName("Montgomery");
//        montgomery.setState(alabama);
//        cityRepository.save(montgomery);
//
//        City mobile = new City();
//        mobile.setName("Mobile");
//        mobile.setState(alabama);
//        cityRepository.save(mobile);
//
//        City huntsville = new City();
//        huntsville.setName("Huntsville");
//        huntsville.setState(alabama);
//        cityRepository.save(huntsville);
//
//        City tuscaloosa = new City();
//        tuscaloosa.setName("Tuscaloosa");
//        tuscaloosa.setState(alabama);
//        cityRepository.save(tuscaloosa);

//// Alaska
//        City anchorage = new City();
//        anchorage.setName("Anchorage");
//        anchorage.setState(alaska);
//        cityRepository.save(anchorage);
//
//        City fairbanks = new City();
//        fairbanks.setName("Fairbanks");
//        fairbanks.setState(alaska);
//        cityRepository.save(fairbanks);
//
//        City juneau = new City();
//        juneau.setName("Juneau");
//        juneau.setState(alaska);
//        cityRepository.save(juneau);
//
//        City sitka = new City();
//        sitka.setName("Sitka");
//        sitka.setState(alaska);
//        cityRepository.save(sitka);
//
//        City ketchikan = new City();
//        ketchikan.setName("Ketchikan");
//        ketchikan.setState(alaska);
//        cityRepository.save(ketchikan);

//// Arizona
//        City phoenix = new City();
//        phoenix.setName("Phoenix");
//        phoenix.setState(arizona);
//        cityRepository.save(phoenix);
//
//        City tucson = new City();
//        tucson.setName("Tucson");
//        tucson.setState(arizona);
//        cityRepository.save(tucson);
//
//        City mesa = new City();
//        mesa.setName("Mesa");
//        mesa.setState(arizona);
//        cityRepository.save(mesa);
//
//        City tempe = new City();
//        tempe.setName("Tempe");
//        tempe.setState(arizona);
//        cityRepository.save(tempe);
//
//        City chandler = new City();
//        chandler.setName("Chandler");
//        chandler.setState(arizona);
//        cityRepository.save(chandler);

//// Arkansas
//        City littleRock = new City();
//        littleRock.setName("Little Rock");
//        littleRock.setState(arkansas);
//        cityRepository.save(littleRock);
//
//        City fortSmith = new City();
//        fortSmith.setName("Fort Smith");
//        fortSmith.setState(arkansas);
//        cityRepository.save(fortSmith);
//
//        City fayetteville = new City();
//        fayetteville.setName("Fayetteville");
//        fayetteville.setState(arkansas);
//        cityRepository.save(fayetteville);
//
//        City springdale = new City();
//        springdale.setName("Springdale");
//        springdale.setState(arkansas);
//        cityRepository.save(springdale);
//
//        City jonesboro = new City();
//        jonesboro.setName("Jonesboro");
//        jonesboro.setState(arkansas);
//        cityRepository.save(jonesboro);

// California
        City losAngeles = new City();
        losAngeles.setName("Los Angeles");
        losAngeles.setState(california);
        cityRepository.save(losAngeles);

        City sanFrancisco = new City();
        sanFrancisco.setName("San Francisco");
        sanFrancisco.setState(california);
        cityRepository.save(sanFrancisco);

//        City sanDiego = new City();
//        sanDiego.setName("San Diego");
//        sanDiego.setState(california);
//        cityRepository.save(sanDiego);
//
//        City sacramento = new City();
//        sacramento.setName("Sacramento");
//        sacramento.setState(california);
//        cityRepository.save(sacramento);
//
//        City sanJose = new City();
//        sanJose.setName("San Jose");
//        sanJose.setState(california);
//        cityRepository.save(sanJose);


        // Colorado
        City denver = new City();
        denver.setName("Denver");
        denver.setState(colorado);
        cityRepository.save(denver);

//        City coloradoSprings = new City();
//        coloradoSprings.setName("Colorado Springs");
//        coloradoSprings.setState(colorado);
//        cityRepository.save(coloradoSprings);
//
//        City aurora = new City();
//        aurora.setName("Aurora");
//        aurora.setState(colorado);
//        cityRepository.save(aurora);
//
//        City fortCollins = new City();
//        fortCollins.setName("Fort Collins");
//        fortCollins.setState(colorado);
//        cityRepository.save(fortCollins);
//
//        City boulder = new City();
//        boulder.setName("Boulder");
//        boulder.setState(colorado);
//        cityRepository.save(boulder);
//
//        // Connecticut
//        City bridgeport = new City();
//        bridgeport.setName("Bridgeport");
//        bridgeport.setState(connecticut);
//        cityRepository.save(bridgeport);
//
//        City newHaven = new City();
//        newHaven.setName("New Haven");
//        newHaven.setState(connecticut);
//        cityRepository.save(newHaven);
//
//        City hartford = new City();
//        hartford.setName("Hartford");
//        hartford.setState(connecticut);
//        cityRepository.save(hartford);
//
//        City stamford = new City();
//        stamford.setName("Stamford");
//        stamford.setState(connecticut);
//        cityRepository.save(stamford);
//
//        City waterbury = new City();
//        waterbury.setName("Waterbury");
//        waterbury.setState(connecticut);
//        cityRepository.save(waterbury);

//// Delaware
//        City wilmington = new City();
//        wilmington.setName("Wilmington");
//        wilmington.setState(delaware);
//        cityRepository.save(wilmington);
//
//        City dover = new City();
//        dover.setName("Dover");
//        dover.setState(delaware);
//        cityRepository.save(dover);
//
//        City newark = new City();
//        newark.setName("Newark");
//        newark.setState(delaware);
//        cityRepository.save(newark);
//
//        City middletown = new City();
//        middletown.setName("Middletown");
//        middletown.setState(delaware);
//        cityRepository.save(middletown);
//
//        City smyrna = new City();
//        smyrna.setName("Smyrna");
//        smyrna.setState(delaware);
//        cityRepository.save(smyrna);

// Florida
//        City jacksonville = new City();
//        jacksonville.setName("Jacksonville");
//        jacksonville.setState(florida);
//        cityRepository.save(jacksonville);

        City miami = new City();
        miami.setName("Miami");
        miami.setState(florida);
        cityRepository.save(miami);
//
//        City tampa = new City();
//        tampa.setName("Tampa");
//        tampa.setState(florida);
//        cityRepository.save(tampa);
//
//        City orlando = new City();
//        orlando.setName("Orlando");
//        orlando.setState(florida);
//        cityRepository.save(orlando);
//
//        City tallahassee = new City();
//        tallahassee.setName("Tallahassee");
//        tallahassee.setState(florida);
//        cityRepository.save(tallahassee);
//
// Georgia
        City atlanta = new City();
        atlanta.setName("Atlanta");
        atlanta.setState(georgia);
        cityRepository.save(atlanta);
//
//        City savannah = new City();
//        savannah.setName("Savannah");
//        savannah.setState(georgia);
//        cityRepository.save(savannah);
//
//        City augusta = new City();
//        augusta.setName("Augusta");
//        augusta.setState(georgia);
//        cityRepository.save(augusta);
//
//        City columbus = new City();
//        columbus.setName("Columbus");
//        columbus.setState(georgia);
//        cityRepository.save(columbus);
//
//        City macon = new City();
//        macon.setName("Macon");
//        macon.setState(georgia);
//        cityRepository.save(macon);
//
//
//        // Hawaii
//        City honolulu = new City();
//        honolulu.setName("Honolulu");
//        honolulu.setState(hawaii);
//        cityRepository.save(honolulu);
//
//        City hilo = new City();
//        hilo.setName("Hilo");
//        hilo.setState(hawaii);
//        cityRepository.save(hilo);
//
//        City kailua = new City();
//        kailua.setName("Kailua");
//        kailua.setState(hawaii);
//        cityRepository.save(kailua);
//
//        City kapolei = new City();
//        kapolei.setName("Kapolei");
//        kapolei.setState(hawaii);
//        cityRepository.save(kapolei);
//
//        City pearlCity = new City();
//        pearlCity.setName("Pearl City");
//        pearlCity.setState(hawaii);
//        cityRepository.save(pearlCity);
//
//// Idaho
//        City boise = new City();
//        boise.setName("Boise");
//        boise.setState(idaho);
//        cityRepository.save(boise);
//
//        City nampa = new City();
//        nampa.setName("Nampa");
//        nampa.setState(idaho);
//        cityRepository.save(nampa);
//
//        City meridian = new City();
//        meridian.setName("Meridian");
//        meridian.setState(idaho);
//        cityRepository.save(meridian);
//
//        City idahoFalls = new City();
//        idahoFalls.setName("Idaho Falls");
//        idahoFalls.setState(idaho);
//        cityRepository.save(idahoFalls);
//
//        City pocatello = new City();
//        pocatello.setName("Pocatello");
//        pocatello.setState(idaho);
//        cityRepository.save(pocatello);
//
//// Illinois
//        City chicago = new City();
//        chicago.setName("Chicago");
//        chicago.setState(illinois);
//        cityRepository.save(chicago);
//
//        City auroraIL = new City();
//        auroraIL.setName("Aurora");
//        auroraIL.setState(illinois);
//        cityRepository.save(auroraIL);
//
//        City rockford = new City();
//        rockford.setName("Rockford");
//        rockford.setState(illinois);
//        cityRepository.save(rockford);
//
//        City joliet = new City();
//        joliet.setName("Joliet");
//        joliet.setState(illinois);
//        cityRepository.save(joliet);
//
//        City naperville = new City();
//        naperville.setName("Naperville");
//        naperville.setState(illinois);
//        cityRepository.save(naperville);
//
//// Indiana
//        City indianapolis = new City();
//        indianapolis.setName("Indianapolis");
//        indianapolis.setState(indiana);
//        cityRepository.save(indianapolis);
//
//        City fortWayne = new City();
//        fortWayne.setName("Fort Wayne");
//        fortWayne.setState(indiana);
//        cityRepository.save(fortWayne);
//
//        City evansville = new City();
//        evansville.setName("Evansville");
//        evansville.setState(indiana);
//        cityRepository.save(evansville);
//
//        City southBend = new City();
//        southBend.setName("South Bend");
//        southBend.setState(indiana);
//        cityRepository.save(southBend);
//
//        City carmel = new City();
//        carmel.setName("Carmel");
//        carmel.setState(indiana);
//        cityRepository.save(carmel);
//
// Iowa
        City desMoines = new City();
        desMoines.setName("Des Moines");
        desMoines.setState(iowa);
        cityRepository.save(desMoines);
//
//        City cedarRapids = new City();
//        cedarRapids.setName("Cedar Rapids");
//        cedarRapids.setState(iowa);
//        cityRepository.save(cedarRapids);bo
//
//        City davenport = new City();
//        davenport.setName("Davenport");
//        davenport.setState(iowa);
//        cityRepository.save(davenport);
//
//        City siouxCity = new City();
//        siouxCity.setName("Sioux City");
//        siouxCity.setState(iowa);
//        cityRepository.save(siouxCity);
//
        City iowaCity = new City();
        iowaCity.setName("Iowa City");
        iowaCity.setState(iowa);
        cityRepository.save(iowaCity);
//
//        // Kansas
//        City wichita = new City();
//        wichita.setName("Wichita");
//        wichita.setState(kansas);
//        cityRepository.save(wichita);
//
//        City overlandPark = new City();
//        overlandPark.setName("Overland Park");
//        overlandPark.setState(kansas);
//        cityRepository.save(overlandPark);
//
//        City kansasCity = new City();
//        kansasCity.setName("Kansas City");
//        kansasCity.setState(kansas);
//        cityRepository.save(kansasCity);
//
//        City olathe = new City();
//        olathe.setName("Olathe");
//        olathe.setState(kansas);
//        cityRepository.save(olathe);
//
//        City topeka = new City();
//        topeka.setName("Topeka");
//        topeka.setState(kansas);
//        cityRepository.save(topeka);
//
//// Kentucky
//        City louisville = new City();
//        louisville.setName("Louisville");
//        louisville.setState(kentucky);
//        cityRepository.save(louisville);
//
//        City lexington = new City();
//        lexington.setName("Lexington");
//        lexington.setState(kentucky);
//        cityRepository.save(lexington);
//
//        City bowlingGreen = new City();
//        bowlingGreen.setName("Bowling Green");
//        bowlingGreen.setState(kentucky);
//        cityRepository.save(bowlingGreen);
//
//        City owensboro = new City();
//        owensboro.setName("Owensboro");
//        owensboro.setState(kentucky);
//        cityRepository.save(owensboro);
//
//        City covington = new City();
//        covington.setName("Covington");
//        covington.setState(kentucky);
//        cityRepository.save(covington);
//
//// Louisiana
//        City newOrleans = new City();
//        newOrleans.setName("New Orleans");
//        newOrleans.setState(louisiana);
//        cityRepository.save(newOrleans);
//
//        City batonRouge = new City();
//        batonRouge.setName("Baton Rouge");
//        batonRouge.setState(louisiana);
//        cityRepository.save(batonRouge);
//
//        City shreveport = new City();
//        shreveport.setName("Shreveport");
//        shreveport.setState(louisiana);
//        cityRepository.save(shreveport);
//
//        City lafayette = new City();
//        lafayette.setName("Lafayette");
//        lafayette.setState(louisiana);
//        cityRepository.save(lafayette);
//
//        City lakeCharles = new City();
//        lakeCharles.setName("Lake Charles");
//        lakeCharles.setState(louisiana);
//        cityRepository.save(lakeCharles);
//
//// Maine
//        City portland = new City();
//        portland.setName("Portland");
//        portland.setState(maine);
//        cityRepository.save(portland);
//
//        City lewiston = new City();
//        lewiston.setName("Lewiston");
//        lewiston.setState(maine);
//        cityRepository.save(lewiston);
//
//        City bangor = new City();
//        bangor.setName("Bangor");
//        bangor.setState(maine);
//        cityRepository.save(bangor);
//
//        City southPortland = new City();
//        southPortland.setName("South Portland");
//        southPortland.setState(maine);
//        cityRepository.save(southPortland);
//
//        City auburn = new City();
//        auburn.setName("Auburn");
//        auburn.setState(maine);
//        cityRepository.save(auburn);
//
//// Maryland
//        City baltimore = new City();
//        baltimore.setName("Baltimore");
//        baltimore.setState(maryland);
//        cityRepository.save(baltimore);
//
//        City frederick = new City();
//        frederick.setName("Frederick");
//        frederick.setState(maryland);
//        cityRepository.save(frederick);
//
//        City rockville = new City();
//        rockville.setName("Rockville");
//        rockville.setState(maryland);
//        cityRepository.save(rockville);
//
//        City gaithersburg = new City();
//        gaithersburg.setName("Gaithersburg");
//        gaithersburg.setState(maryland);
//        cityRepository.save(gaithersburg);
//
//        City bowie = new City();
//        bowie.setName("Bowie");
//        bowie.setState(maryland);
//        cityRepository.save(bowie);
//
// Massachusetts
        City boston = new City();
        boston.setName("Boston");
        boston.setState(massachusetts);
        cityRepository.save(boston);
//
//        City worcester = new City();
//        worcester.setName("Worcester");
//        worcester.setState(massachusetts);
//        cityRepository.save(worcester);
//
//        City springfield = new City();
//        springfield.setName("Springfield");
//        springfield.setState(massachusetts);
//        cityRepository.save(springfield);
//
//        City cambridge = new City();
//        cambridge.setName("Cambridge");
//        cambridge.setState(massachusetts);
//        cityRepository.save(cambridge);
//
//        City lowell = new City();
//        lowell.setName("Lowell");
//        lowell.setState(massachusetts);
//        cityRepository.save(lowell);
//
//// Michigan
//        City detroit = new City();
//        detroit.setName("Detroit");
//        detroit.setState(michigan);
//        cityRepository.save(detroit);
//
//        City grandRapids = new City();
//        grandRapids.setName("Grand Rapids");
//        grandRapids.setState(michigan);
//        cityRepository.save(grandRapids);
//
//        City warren = new City();
//        warren.setName("Warren");
//        warren.setState(michigan);
//        cityRepository.save(warren);
//
//        City sterlingHeights = new City();
//        sterlingHeights.setName("Sterling Heights");
//        sterlingHeights.setState(michigan);
//        cityRepository.save(sterlingHeights);
//
//        City annArbor = new City();
//        annArbor.setName("Ann Arbor");
//        annArbor.setState(michigan);
//        cityRepository.save(annArbor);
//
//// Minnesota
//        City minneapolis = new City();
//        minneapolis.setName("Minneapolis");
//        minneapolis.setState(minnesota);
//        cityRepository.save(minneapolis);
//
//        City saintPaul = new City();
//        saintPaul.setName("Saint Paul");
//        saintPaul.setState(minnesota);
//        cityRepository.save(saintPaul);
//
//        City rochester = new City();
//        rochester.setName("Rochester");
//        rochester.setState(minnesota);
//        cityRepository.save(rochester);
//
//        City bloomington = new City();
//        bloomington.setName("Bloomington");
//        bloomington.setState(minnesota);
//        cityRepository.save(bloomington);
//
//        City duluth = new City();
//        duluth.setName("Duluth");
//        duluth.setState(minnesota);
//        cityRepository.save(duluth);
//
//// Mississippi
//        City jackson = new City();
//        jackson.setName("Jackson");
//        jackson.setState(mississippi);
//        cityRepository.save(jackson);
//
//        City gulfport = new City();
//        gulfport.setName("Gulfport");
//        gulfport.setState(mississippi);
//        cityRepository.save(gulfport);
//
//        City southaven = new City();
//        southaven.setName("Southaven");
//        southaven.setState(mississippi);
//        cityRepository.save(southaven);
//
//        City hattiesburg = new City();
//        hattiesburg.setName("Hattiesburg");
//        hattiesburg.setState(mississippi);
//        cityRepository.save(hattiesburg);
//
//        City biloxi = new City();
//        biloxi.setName("Biloxi");
//        biloxi.setState(mississippi);
//        cityRepository.save(biloxi);
//
//// Missouri
//        City kansasCityMO = new City();
//        kansasCityMO.setName("Kansas City");
//        kansasCityMO.setState(missouri);
//        cityRepository.save(kansasCityMO);
//
//        City saintLouis = new City();
//        saintLouis.setName("St. Louis");
//        saintLouis.setState(missouri);
//        cityRepository.save(saintLouis);
//
//        City springfieldMO = new City();
//        springfieldMO.setName("Springfield");
//        springfieldMO.setState(missouri);
//        cityRepository.save(springfieldMO);
//
//        City independence = new City();
//        independence.setName("Independence");
//        independence.setState(missouri);
//        cityRepository.save(independence);
//
//        City columbia = new City();
//        columbia.setName("Columbia");
//        columbia.setState(missouri);
//        cityRepository.save(columbia);
//
//// Montana
//        City billings = new City();
//        billings.setName("Billings");
//        billings.setState(montana);
//        cityRepository.save(billings);
//
//        City missoula = new City();
//        missoula.setName("Missoula");
//        missoula.setState(montana);
//        cityRepository.save(missoula);
//
//        City greatFalls = new City();
//        greatFalls.setName("Great Falls");
//        greatFalls.setState(montana);
//        cityRepository.save(greatFalls);
//
//        City bozeman = new City();
//        bozeman.setName("Bozeman");
//        bozeman.setState(montana);
//        cityRepository.save(bozeman);
//
//        City helena = new City();
//        helena.setName("Helena");
//        helena.setState(montana);
//        cityRepository.save(helena);
//
//// Nebraska
//        City omaha = new City();
//        omaha.setName("Omaha");
//        omaha.setState(nebraska);
//        cityRepository.save(omaha);
//
//        City lincoln = new City();
//        lincoln.setName("Lincoln");
//        lincoln.setState(nebraska);
//        cityRepository.save(lincoln);
//
//        City bellevue = new City();
//        bellevue.setName("Bellevue");
//        bellevue.setState(nebraska);
//        cityRepository.save(bellevue);
//
//        City grandIsland = new City();
//        grandIsland.setName("Grand Island");
//        grandIsland.setState(nebraska);
//        cityRepository.save(grandIsland);
//
//        City kearney = new City();
//        kearney.setName("Kearney");
//        kearney.setState(nebraska);
//        cityRepository.save(kearney);
//
//// Nevada
//        City lasVegas = new City();
//        lasVegas.setName("Las Vegas");
//        lasVegas.setState(nevada);
//        cityRepository.save(lasVegas);
//
//        City henderson = new City();
//        henderson.setName("Henderson");
//        henderson.setState(nevada);
//        cityRepository.save(henderson);
//
//        City reno = new City();
//        reno.setName("Reno");
//        reno.setState(nevada);
//        cityRepository.save(reno);
//
//        City northLasVegas = new City();
//        northLasVegas.setName("North Las Vegas");
//        northLasVegas.setState(nevada);
//        cityRepository.save(northLasVegas);
//
//        City sparks = new City();
//        sparks.setName("Sparks");
//        sparks.setState(nevada);
//        cityRepository.save(sparks);
//
//// New Hampshire
//        City manchester = new City();
//        manchester.setName("Manchester");
//        manchester.setState(newHampshire);
//        cityRepository.save(manchester);
//
//        City nashua = new City();
//        nashua.setName("Nashua");
//        nashua.setState(newHampshire);
//        cityRepository.save(nashua);
//
//        City concord = new City();
//        concord.setName("Concord");
//        concord.setState(newHampshire);
//        cityRepository.save(concord);
//
//        City derry = new City();
//        derry.setName("Derry");
//        derry.setState(newHampshire);
//        cityRepository.save(derry);
//
//        City rochesterNH = new City();
//        rochesterNH.setName("Rochester");
//        rochesterNH.setState(newHampshire);
//        cityRepository.save(rochesterNH);
//
//// New Jersey
//        City newarkNJ = new City();
//        newarkNJ.setName("Newark");
//        newarkNJ.setState(newJersey);
//        cityRepository.save(newarkNJ);
//
//        City jerseyCity = new City();
//        jerseyCity.setName("Jersey City");
//        jerseyCity.setState(newJersey);
//        cityRepository.save(jerseyCity);
//
//        City paterson = new City();
//        paterson.setName("Paterson");
//        paterson.setState(newJersey);
//        cityRepository.save(paterson);
//
//        City elizabeth = new City();
//        elizabeth.setName("Elizabeth");
//        elizabeth.setState(newJersey);
//        cityRepository.save(elizabeth);
//
//        City edison = new City();
//        edison.setName("Edison");
//        edison.setState(newJersey);
//        cityRepository.save(edison);
//
//// New Mexico
//        City albuquerque = new City();
//        albuquerque.setName("Albuquerque");
//        albuquerque.setState(newMexico);
//        cityRepository.save(albuquerque);
//
//        City lasCruces = new City();
//        lasCruces.setName("Las Cruces");
//        lasCruces.setState(newMexico);
//        cityRepository.save(lasCruces);
//
//        City rioRancho = new City();
//        rioRancho.setName("Rio Rancho");
//        rioRancho.setState(newMexico);
//        cityRepository.save(rioRancho);
//
//        City santaFe = new City();
//        santaFe.setName("Santa Fe");
//        santaFe.setState(newMexico);
//        cityRepository.save(santaFe);
//
//        City roswell = new City();
//        roswell.setName("Roswell");
//        roswell.setState(newMexico);
//        cityRepository.save(roswell);
//
// New York
        City newYorkNY = new City();
        newYorkNY.setName("New York");
        newYorkNY.setState(newYork);
        cityRepository.save(newYorkNY);

//        City buffalo = new City();
//        buffalo.setName("Buffalo");
//        buffalo.setState(newYork);
//        cityRepository.save(buffalo);
//
//        City rochesterNY = new City();
//        rochesterNY.setName("Rochester");
//        rochesterNY.setState(newYork);
//        cityRepository.save(rochesterNY);
//
//        City yonkers = new City();
//        yonkers.setName("Yonkers");
//        yonkers.setState(newYork);
//        cityRepository.save(yonkers);
//
//        City syracuse = new City();
//        syracuse.setName("Syracuse");
//        syracuse.setState(newYork);
//        cityRepository.save(syracuse);
//
//// North Carolina
//        City charlotte = new City();
//        charlotte.setName("Charlotte");
//        charlotte.setState(northCarolina);
//        cityRepository.save(charlotte);
//
//        City raleigh = new City();
//        raleigh.setName("Raleigh");
//        raleigh.setState(northCarolina);
//        cityRepository.save(raleigh);
//
//        City greensboro = new City();
//        greensboro.setName("Greensboro");
//        greensboro.setState(northCarolina);
//        cityRepository.save(greensboro);
//
//        City durham = new City();
//        durham.setName("Durham");
//        durham.setState(northCarolina);
//        cityRepository.save(durham);
//
//        City winstonSalem = new City();
//        winstonSalem.setName("Winston-Salem");
//        winstonSalem.setState(northCarolina);
//        cityRepository.save(winstonSalem);
//
//// North Dakota
//        City fargo = new City();
//        fargo.setName("Fargo");
//        fargo.setState(northDakota);
//        cityRepository.save(fargo);
//
//        City bismarck = new City();
//        bismarck.setName("Bismarck");
//        bismarck.setState(northDakota);
//        cityRepository.save(bismarck);
//
//        City grandForks = new City();
//        grandForks.setName("Grand Forks");
//        grandForks.setState(northDakota);
//        cityRepository.save(grandForks);
//
//        City minot = new City();
//        minot.setName("Minot");
//        minot.setState(northDakota);
//        cityRepository.save(minot);
//
//        City westFargo = new City();
//        westFargo.setName("West Fargo");
//        westFargo.setState(northDakota);
//        cityRepository.save(westFargo);
//
// Ohio
        City columbusOH = new City();
        columbusOH.setName("Columbus");
        columbusOH.setState(ohio);
        cityRepository.save(columbusOH);

//        City cleveland = new City();
//        cleveland.setName("Cleveland");
//        cleveland.setState(ohio);
//        cityRepository.save(cleveland);
//
//        City cincinnati = new City();
//        cincinnati.setName("Cincinnati");
//        cincinnati.setState(ohio);
//        cityRepository.save(cincinnati);
//
//        City toledo = new City();
//        toledo.setName("Toledo");
//        toledo.setState(ohio);
//        cityRepository.save(toledo);
//
//        City akron = new City();
//        akron.setName("Akron");
//        akron.setState(ohio);
//        cityRepository.save(akron);
//
//// Oklahoma
//        City oklahomaCity = new City();
//        oklahomaCity.setName("Oklahoma City");
//        oklahomaCity.setState(oklahoma);
//        cityRepository.save(oklahomaCity);
//
//        City tulsa = new City();
//        tulsa.setName("Tulsa");
//        tulsa.setState(oklahoma);
//        cityRepository.save(tulsa);
//
//        City norman = new City();
//        norman.setName("Norman");
//        norman.setState(oklahoma);
//        cityRepository.save(norman);
//
//        City brokenArrow = new City();
//        brokenArrow.setName("Broken Arrow");
//        brokenArrow.setState(oklahoma);
//        cityRepository.save(brokenArrow);
//
//        City lawton = new City();
//        lawton.setName("Lawton");
//        lawton.setState(oklahoma);
//        cityRepository.save(lawton);
//
// Oregon
        City portlandOR = new City();
        portlandOR.setName("Portland");
        portlandOR.setState(oregon);
        cityRepository.save(portlandOR);
//
//        City eugene = new City();
//        eugene.setName("Eugene");
//        eugene.setState(oregon);
//        cityRepository.save(eugene);
//
//        City salem = new City();
//        salem.setName("Salem");
//        salem.setState(oregon);
//        cityRepository.save(salem);
//
//        City gresham = new City();
//        gresham.setName("Gresham");
//        gresham.setState(oregon);
//        cityRepository.save(gresham);
//
//        City hillsboro = new City();
//        hillsboro.setName("Hillsboro");
//        hillsboro.setState(oregon);
//        cityRepository.save(hillsboro);
//
//// Pennsylvania
//        City philadelphia = new City();
//        philadelphia.setName("Philadelphia");
//        philadelphia.setState(pennsylvania);
//        cityRepository.save(philadelphia);
//
//        City pittsburgh = new City();
//        pittsburgh.setName("Pittsburgh");
//        pittsburgh.setState(pennsylvania);
//        cityRepository.save(pittsburgh);
//
//        City allentown = new City();
//        allentown.setName("Allentown");
//        allentown.setState(pennsylvania);
//        cityRepository.save(allentown);
//
//        City erie = new City();
//        erie.setName("Erie");
//        erie.setState(pennsylvania);
//        cityRepository.save(erie);
//
//        City reading = new City();
//        reading.setName("Reading");
//        reading.setState(pennsylvania);
//        cityRepository.save(reading);
//
//// Rhode Island
//        City providence = new City();
//        providence.setName("Providence");
//        providence.setState(rhodeIsland);
//        cityRepository.save(providence);
//
//        City warwick = new City();
//        warwick.setName("Warwick");
//        warwick.setState(rhodeIsland);
//        cityRepository.save(warwick);
//
//        City cranston = new City();
//        cranston.setName("Cranston");
//        cranston.setState(rhodeIsland);
//        cityRepository.save(cranston);
//
//        City pawtucket = new City();
//        pawtucket.setName("Pawtucket");
//        pawtucket.setState(rhodeIsland);
//        cityRepository.save(pawtucket);
//
//        City eastProvidence = new City();
//        eastProvidence.setName("East Providence");
//        eastProvidence.setState(rhodeIsland);
//        cityRepository.save(eastProvidence);
//
//// South Carolina
//        City columbiaSC = new City();
//        columbiaSC.setName("Columbia");
//        columbiaSC.setState(southCarolina);
//        cityRepository.save(columbiaSC);
//
//        City charlestonSC = new City();
//        charlestonSC.setName("Charleston");
//        charlestonSC.setState(southCarolina);
//        cityRepository.save(charlestonSC);
//
//        City northCharleston = new City();
//        northCharleston.setName("North Charleston");
//        northCharleston.setState(southCarolina);
//        cityRepository.save(northCharleston);
//
//        City mountPleasant = new City();
//        mountPleasant.setName("Mount Pleasant");
//        mountPleasant.setState(southCarolina);
//        cityRepository.save(mountPleasant);
//
//        City rockHill = new City();
//        rockHill.setName("Rock Hill");
//        rockHill.setState(southCarolina);
//        cityRepository.save(rockHill);
//
//// South Dakota
//        City siouxFalls = new City();
//        siouxFalls.setName("Sioux Falls");
//        siouxFalls.setState(southDakota);
//        cityRepository.save(siouxFalls);
//
//        City rapidCity = new City();
//        rapidCity.setName("Rapid City");
//        rapidCity.setState(southDakota);
//        cityRepository.save(rapidCity);
//
//        City aberdeen = new City();
//        aberdeen.setName("Aberdeen");
//        aberdeen.setState(southDakota);
//        cityRepository.save(aberdeen);
//
//        City brookings = new City();
//        brookings.setName("Brookings");
//        brookings.setState(southDakota);
//        cityRepository.save(brookings);
//
//        City watertown = new City();
//        watertown.setName("Watertown");
//        watertown.setState(southDakota);
//        cityRepository.save(watertown);
//
//// Tennessee
//        City memphis = new City();
//        memphis.setName("Memphis");
//        memphis.setState(tennessee);
//        cityRepository.save(memphis);
//
//        City nashvilleTN = new City();
//        nashvilleTN.setName("Nashville");
//        nashvilleTN.setState(tennessee);
//        cityRepository.save(nashvilleTN);
//
//        City knoxville = new City();
//        knoxville.setName("Knoxville");
//        knoxville.setState(tennessee);
//        cityRepository.save(knoxville);
//
//        City chattanooga = new City();
//        chattanooga.setName("Chattanooga");
//        chattanooga.setState(tennessee);
//        cityRepository.save(chattanooga);
//
//        City clarksville = new City();
//        clarksville.setName("Clarksville");
//        clarksville.setState(tennessee);
//        cityRepository.save(clarksville);
//
//// Texas
//        City houston = new City();
//        houston.setName("Houston");
//        houston.setState(texas);
//        cityRepository.save(houston);
//
//        City sanAntonio = new City();
//        sanAntonio.setName("San Antonio");
//        sanAntonio.setState(texas);
//        cityRepository.save(sanAntonio);
//
//        City dallas = new City();
//        dallas.setName("Dallas");
//        dallas.setState(texas);
//        cityRepository.save(dallas);
//
//        City austin = new City();
//        austin.setName("Austin");
//        austin.setState(texas);
//        cityRepository.save(austin);
//
//        City fortWorth = new City();
//        fortWorth.setName("Fort Worth");
//        fortWorth.setState(texas);
//        cityRepository.save(fortWorth);
//
//// Utah
//        City saltLakeCity = new City();
//        saltLakeCity.setName("Salt Lake City");
//        saltLakeCity.setState(utah);
//        cityRepository.save(saltLakeCity);
//
//        City westValleyCity = new City();
//        westValleyCity.setName("West Valley City");
//        westValleyCity.setState(utah);
//        cityRepository.save(westValleyCity);
//
//        City provo = new City();
//        provo.setName("Provo");
//        provo.setState(utah);
//        cityRepository.save(provo);
//
//        City westJordan = new City();
//        westJordan.setName("West Jordan");
//        westJordan.setState(utah);
//        cityRepository.save(westJordan);
//
//        City orem = new City();
//        orem.setName("Orem");
//        orem.setState(utah);
//        cityRepository.save(orem);

//// Vermont
//        City burlington = new City();
//        burlington.setName("Burlington");
//        burlington.setState(vermont);
//        cityRepository.save(burlington);
//
//        City southBurlington = new City();
//        southBurlington.setName("South Burlington");
//        southBurlington.setState(vermont);
//        cityRepository.save(southBurlington);
//
//        City rutland = new City();
//        rutland.setName("Rutland");
//        rutland.setState(vermont);
//        cityRepository.save(rutland);
//
//        City barre = new City();
//        barre.setName("Barre");
//        barre.setState(vermont);
//        cityRepository.save(barre);
//
//        City montpelier = new City();
//        montpelier.setName("Montpelier");
//        montpelier.setState(vermont);
//        cityRepository.save(montpelier);
//
//// Virginia
//        City virginiaBeach = new City();
//        virginiaBeach.setName("Virginia Beach");
//        virginiaBeach.setState(virginia);
//        cityRepository.save(virginiaBeach);
//
//        City norfolk = new City();
//        norfolk.setName("Norfolk");
//        norfolk.setState(virginia);
//        cityRepository.save(norfolk);
//
//        City chesapeake = new City();
//        chesapeake.setName("Chesapeake");
//        chesapeake.setState(virginia);
//        cityRepository.save(chesapeake);
//
//        City richmond = new City();
//        richmond.setName("Richmond");
//        richmond.setState(virginia);
//        cityRepository.save(richmond);
//
//        City arlington = new City();
//        arlington.setName("Arlington");
//        arlington.setState(virginia);
//        cityRepository.save(arlington);
//
//// Washington
//        City seattle = new City();
//        seattle.setName("Seattle");
//        seattle.setState(washington);
//        cityRepository.save(seattle);
//
//        City spokane = new City();
//        spokane.setName("Spokane");
//        spokane.setState(washington);
//        cityRepository.save(spokane);
//
//        City tacoma = new City();
//        tacoma.setName("Tacoma");
//        tacoma.setState(washington);
//        cityRepository.save(tacoma);
//
//        City vancouver = new City();
//        vancouver.setName("Vancouver");
//        vancouver.setState(washington);
//        cityRepository.save(vancouver);
//
//        City bellevueWA = new City();
//        bellevueWA.setName("Bellevue");
//        bellevueWA.setState(washington);
//        cityRepository.save(bellevueWA);
//
//// West Virginia
//        City charlestonWV = new City();
//        charlestonWV.setName("Charleston");
//        charlestonWV.setState(westVirginia);
//        cityRepository.save(charlestonWV);
//
//        City huntington = new City();
//        huntington.setName("Huntington");
//        huntington.setState(westVirginia);
//        cityRepository.save(huntington);
//
//        City parkersburg = new City();
//        parkersburg.setName("Parkersburg");
//        parkersburg.setState(westVirginia);
//        cityRepository.save(parkersburg);
//
//        City morgantown = new City();
//        morgantown.setName("Morgantown");
//        morgantown.setState(westVirginia);
//        cityRepository.save(morgantown);
//
//        City wheeling = new City();
//        wheeling.setName("Wheeling");
//        wheeling.setState(westVirginia);
//        cityRepository.save(wheeling);
//
//// Wisconsin
//        City milwaukee = new City();
//        milwaukee.setName("Milwaukee");
//        milwaukee.setState(wisconsin);
//        cityRepository.save(milwaukee);
//
//        City madison = new City();
//        madison.setName("Madison");
//        madison.setState(wisconsin);
//        cityRepository.save(madison);
//
//        City greenBay = new City();
//        greenBay.setName("Green Bay");
//        greenBay.setState(wisconsin);
//        cityRepository.save(greenBay);
//
//        City kenosha = new City();
//        kenosha.setName("Kenosha");
//        kenosha.setState(wisconsin);
//        cityRepository.save(kenosha);
//
//        City racine = new City();
//        racine.setName("Racine");
//        racine.setState(wisconsin);
//        cityRepository.save(racine);
//
//// Wyoming
//        City cheyenne = new City();
//        cheyenne.setName("Cheyenne");
//        cheyenne.setState(wyoming);
//        cityRepository.save(cheyenne);
//
//        City casper = new City();
//        casper.setName("Casper");
//        casper.setState(wyoming);
//        cityRepository.save(casper);
//
//        City laramie = new City();
//        laramie.setName("Laramie");
//        laramie.setState(wyoming);
//        cityRepository.save(laramie);
//
//        City gillette = new City();
//        gillette.setName("Gillette");
//        gillette.setState(wyoming);
//        cityRepository.save(gillette);
//
//        City rockSprings = new City();
//        rockSprings.setName("Rock Springs");
//        rockSprings.setState(wyoming);
//        cityRepository.save(rockSprings);



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
        johnDoe.setState(california);
        johnDoe.setSpecialty(cardiology);
        johnDoe.setCity(losAngeles);
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
        lisaSmith.setState(california);
        lisaSmith.setSpecialty(neurology);
        lisaSmith.setCity(sanFrancisco);
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
        sarahJohnson.setState(newYork);
        sarahJohnson.setSpecialty(pediatrics);
        sarahJohnson.setCity(newYorkNY);
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
        michaelBrown.setState(oregon);
        michaelBrown.setSpecialty(dermatology);
        michaelBrown.setCity(portlandOR);
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
        ivanGeorgiev.setState(ohio);
        ivanGeorgiev.setSpecialty(cardiology);
        ivanGeorgiev.setCity(columbusOH);
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
        mariaPetrova.setState(massachusetts);
        mariaPetrova.setSpecialty(neurology);
        mariaPetrova.setCity(boston);
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
        petarIliev.setState(iowa);
        petarIliev.setSpecialty(dermatology);
        petarIliev.setCity(desMoines);
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
        stanleyRivera.setState(iowa);
        stanleyRivera.setSpecialty(ophthalmology);
        stanleyRivera.setCity(iowaCity);
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
        jolieRose.setState(georgia);
        jolieRose.setSpecialty(urology);
        jolieRose.setCity(atlanta);
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
        loganFlynn.setState(georgia);
        loganFlynn.setSpecialty(oncology);
        loganFlynn.setCity(atlanta);
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
        skylaHodge.setState(california);
        skylaHodge.setSpecialty(orthopedics);
        skylaHodge.setCity(losAngeles);
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
        rebeccaPeters.setState(florida);
        rebeccaPeters.setSpecialty(gastroenterology);
        rebeccaPeters.setCity(miami);
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
        alanPollard.setState(florida);
        alanPollard.setSpecialty(pediatrics);
        alanPollard.setCity(miami);
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
        borisKamenov.setState(florida);
        borisKamenov.setSpecialty(ophthalmology);
        borisKamenov.setCity(miami);
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
        sheiLu.setState(colorado);
        sheiLu.setSpecialty(dermatology);
        sheiLu.setCity(denver);
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