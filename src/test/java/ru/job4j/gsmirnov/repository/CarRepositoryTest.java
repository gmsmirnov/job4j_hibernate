package ru.job4j.gsmirnov.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.gsmirnov.models.Body;
import ru.job4j.gsmirnov.models.Car;
import ru.job4j.gsmirnov.models.Engine;
import ru.job4j.gsmirnov.models.Transmission;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarRepositoryTest {
    private CarRepository cr = new CarRepository();
    private BodyRepository br = new BodyRepository();
    private EngineRepository er = new EngineRepository();
    private TransmissionRepository tr = new TransmissionRepository();

    @Before
    public void init() {
        this.cr.deleteAll();
        this.br.deleteAll();
        this.er.deleteAll();
        this.tr.deleteAll();
    }

    @After
    public void clear() {
    }

    @Test
    public void whenAddCarToDbThenDbHasIt() {
        Body body = new Body("Sedan", "SED55556");
        Engine engine = new Engine("V6", "V666666");
        Transmission transmission = new Transmission("Variator", "VAR55556");
        body = this.br.add(body);
        engine = er.add(engine);
        transmission = tr.add(transmission);

        Car car = new Car("tachka", body, engine, transmission);
        car = this.cr.add(car);

        Car carHib = this.cr.findById(car.getId());
        assertThat(carHib.equals(car), is(true));
        assertThat(carHib.getId(), is(car.getId()));
        assertThat(carHib.getName(), is(car.getName()));
        assertThat(carHib.getBody(), is(body));
        assertThat(carHib.getEngine(), is(engine));
        assertThat(carHib.getTransmission(), is(transmission));
    }

    @Test
    public void whenUpdatesCarInDbThenDbHasChanges() {
        Body body = new Body("Sedan", "SED55556");
        Engine engine = new Engine("V6", "V666666");
        Transmission transmission = new Transmission("Variator", "VAR55556");
        body = this.br.add(body);
        engine = er.add(engine);
        transmission = tr.add(transmission);

        Car car = new Car("tachka", body, engine, transmission);
        car = this.cr.add(car);
        Car carHib = this.cr.findById(car.getId());

        String newName = "TAZ";
        car.setName(newName);
        car = this.cr.update(car);

        assertThat(carHib.equals(car), is(false));
        assertThat(carHib.getId(), is(car.getId()));
        assertThat(carHib.getName().equals(newName), is(false));
        assertThat(car.getName().equals(newName), is(true));
    }

    @Test
    public void whenDeletesCarFromDbThenDbHasNotIt() {
        Body body = new Body("Sedan", "SED55556");
        Engine engine = new Engine("V6", "V666666");
        Transmission transmission = new Transmission("Variator", "VAR55556");
        body = this.br.add(body);
        engine = er.add(engine);
        transmission = tr.add(transmission);

        Car car = new Car("tachka", body, engine, transmission);
        car = this.cr.add(car);
        this.cr.delete(car.getId());
        Car carHib = this.cr.findById(car.getId());

        assertThat(carHib.getId(), is(-1));
    }

    @Test
    public void whenNoCarWithSuchIdThenTrue() {
        assertThat(this.cr.findAll().size(), is(0));
        Car car = this.cr.findById(1000);
        assertThat(car.getId(), is(-1));
    }

    @Test
    public void whenSearchesByNameThenFoundBodiesWithThatName() {
        Body[] bodies = new Body[]{
                new Body("Sedan", "SED111111"),
                new Body("Sedan", "SED222222"),
                new Body("Universal", "UNI111111"),
                new Body("Universal", "UNI222222"),
                new Body("Universal", "UNI333333")
        };
        Engine[] engines = new Engine[]{
                new Engine("V6", "V666666"),
                new Engine("V6", "V666667"),
                new Engine("V12", "V121212"),
                new Engine("V12", "V131313"),
                new Engine("V12", "V141414")
        };
        Transmission[] tss = new Transmission[]{
                new Transmission("AKPP", "A111111"),
                new Transmission("AKPP", "A222222"),
                new Transmission("MKPP", "M111111"),
                new Transmission("MKPP", "M222222"),
                new Transmission("MKPP", "M333333")
        };
        Car[] cars = new Car[]{
                new Car("tachka", bodies[0], engines[0], tss[0]),
                new Car("tachka", bodies[1], engines[1], tss[1]),
                new Car("tachka", bodies[2], engines[2], tss[2]),
                new Car("vedro", bodies[3], engines[3], tss[3]),
                new Car("taz", bodies[4], engines[4], tss[4])
        };
        Arrays.asList(bodies).forEach(this.br::add);
        Arrays.asList(engines).forEach(this.er::add);
        Arrays.asList(tss).forEach(this.tr::add);
        Arrays.asList(cars).forEach(this.cr::add);
        String searchName = "tachka";
        List<Car> carSearch = this.cr.findCarsByName(searchName);
        assertThat(carSearch.size(), is(3));
        assertThat(carSearch, containsInAnyOrder(cars[0], cars[1], cars[2]));
    }
}