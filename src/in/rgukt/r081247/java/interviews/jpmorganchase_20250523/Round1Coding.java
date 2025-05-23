package in.rgukt.r081247.java.interviews.jpmorganchase_20250523;

import java.util.*;

public class Round1Coding {
    /*
    Get top 3 country, state population
    Only one state from a country should be in results
     */
    public static class Country {
        public Country() {

        }
        public Country(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Country country = (Country) o;
            return Objects.equals(id, country.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        public Long id;
        public String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class State {
        public State() {

        }
        public State(Long id, String name, Country country) {
            this.id = id;
            this.name = name;
            this.country = country;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;
            return Objects.equals(id, state.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        public Long id;
        public String name;
        public Country country;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "State{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", country=" + country +
                    '}';
        }
    }

    public static class City {
        public City() {

        }
        public City(Long id, String name, State state, Long population) {
            this.id = id;
            this.name = name;
            this.state = state;
            this.population = population;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            City city = (City) o;
            return Objects.equals(id, city.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        public Long id;
        public String name;
        public State state;
        public Long population;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public Long getPopulation() {
            return population;
        }

        public void setPopulation(Long population) {
            this.population = population;
        }

        @Override
        public String toString() {
            return "City{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", state=" + state +
                    ", population=" + population +
                    '}';
        }
    }

    public static Map<Country, List<State>> countryStateMap = new HashMap<>();
    public static Map<State, List<City>> stateCityMap = new HashMap<>();

    static {
        Country india = new Country(1L, "India");

        State andhra = new State(101L, "Andhra Pradesh", india);
        City nellore = new City(1001L, "Nellore", andhra, 10000L);
        City kadapa = new City(1002L, "Kadapa", andhra, 20000L);
        stateCityMap.put(andhra, List.of(nellore, kadapa));

        State tamil = new State(102L, "Tamil Nadu", india);
        City chennai = new City(1003L, "Chennai", tamil, 10000L);
        stateCityMap.put(tamil, List.of(chennai));

        countryStateMap.put(india, List.of(andhra, tamil));


        Country usa = new Country(2L, "USA");

        State texas = new State(111L, "Texas", usa);
        City texascity1 = new City(2001L, "texascity1", texas, 5000L);
        stateCityMap.put(texas, List.of(texascity1));

        countryStateMap.put(usa, List.of(texas));

        Country aus = new Country(3L, "Australia");

        State newsoutwales = new State(121L, "New South Wales", aus);
        City ns1 = new City(3001L, "NS1", newsoutwales, 6000L);
        stateCityMap.put(newsoutwales, List.of(ns1));

        State oldsoutwales = new State(122L, "Old South Wales", aus);
        City os1 = new City(3002L, "OS1", oldsoutwales, 8000L);
        stateCityMap.put(oldsoutwales, List.of(os1));

        countryStateMap.put(aus, List.of(newsoutwales, oldsoutwales));

        Country uk = new Country(4L, "UK");

        State france = new State(131L, "France", uk);
        City france1 = new City(4001L, "france1", france, 2000L);
        City france2 = new City(4002L, "france2", france, 3000L);
        stateCityMap.put(france, List.of(france1, france2));

        countryStateMap.put(uk, List.of(france));

        Country pakistan = new Country(5L, "Pakistam");

        State istambul = new State(141L, "Istambul", pakistan);

        stateCityMap.put(istambul, List.of());
        countryStateMap.put(pakistan, List.of(istambul));

    }

    public static class CountryState {
        public CountryState() {

        }

        public CountryState(Long countryId, String countryName, Long stateId, String stateName, Long population) {
            this.countryId = countryId;
            this.countryName = countryName;
            this.stateId = stateId;
            this.stateName = stateName;
            this.population = population;
        }
        public Long countryId;
        public String countryName;
        public Long stateId;
        public String stateName;
        public Long population;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CountryState that = (CountryState) o;
            return Objects.equals(countryId, that.countryId) && Objects.equals(stateId, that.stateId);
        }

        @Override
        public int hashCode() {
            int result = Objects.hashCode(countryId);
            result = 31 * result + Objects.hashCode(stateId);
            return result;
        }

        public Long getCountryId() {
            return countryId;
        }

        public void setCountryId(Long countryId) {
            this.countryId = countryId;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public Long getStateId() {
            return stateId;
        }

        public void setStateId(Long stateId) {
            this.stateId = stateId;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public Long getPopulation() {
            return population;
        }

        public void setPopulation(Long population) {
            this.population = population;
        }

        @Override
        public String toString() {
            return "CountryState{" +
                    "countryId=" + countryId +
                    ", countryName='" + countryName + '\'' +
                    ", stateId=" + stateId +
                    ", stateName='" + stateName + '\'' +
                    ", population=" + population +
                    '}';
        }
    }
    public static void main(String [] args) {
        List<CountryState> countryLevel = new ArrayList<>();
        for(Country country: countryStateMap.keySet()) {
            List<CountryState> countryStateLevel = new ArrayList<>();
            for(State state: countryStateMap.get(country)) {
                CountryState countryState = new CountryState(country.getId(), country.getName(), state.getId(), state.getName(), 0L);
                for(City city: stateCityMap.get(state)) {
                    if(city.getPopulation() == null)
                        city.setPopulation(5000L);
                    countryState.setPopulation(countryState.getPopulation() + city.getPopulation());
                }
                countryStateLevel.add(countryState);
            }
            System.out.println(countryStateLevel);
            Optional<CountryState> maxCountryStateLevel = countryStateLevel.stream().max(Comparator.comparing(CountryState::getPopulation));
            if(maxCountryStateLevel.isPresent())
                countryLevel.add(maxCountryStateLevel.get());
        }
        System.out.println(countryLevel);
        List<CountryState> maxCountryLevel = countryLevel.stream()
                .sorted(Comparator.comparing(CountryState::getPopulation).reversed())
                .limit(3)
                .toList();
        System.out.println(maxCountryLevel);
    }
}
