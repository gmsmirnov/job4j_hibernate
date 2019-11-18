package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.gsmirnov.models.Engine;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class EngineRepositoryTest {
    private final static Logger LOG = LogManager.getLogger(EngineRepositoryTest.class);

    private final EngineRepository er = new EngineRepository();

    @Before
    public void init() {
        er.deleteAll();
    }

    @Test
    public void whenAddEngineToDbThenDbHasIt() {
        String name = "V6";
        String sn = "V666666";
        Engine engine = new Engine(name, sn);
        engine = er.add(engine);
        Engine engineHib = er.findById(engine.getId());
        assertThat(engineHib.equals(engine), is(true));
        assertThat(engineHib.getId(), is(engine.getId()));
        assertThat(engineHib.getName(), is(name));
        assertThat(engineHib.getSerialNumber(), is(sn));
    }

    @Test
    public void whenUpdatesEngineInDbThenDbHasChanges() {
        String name = "V6";
        String sn = "V666666";
        Engine engine = new Engine(name, sn);
        engine = er.add(engine);
        Engine engineHib = er.findById(engine.getId());
        assertThat(engineHib.equals(engine), is(true));
        assertThat(engineHib.getId(), is(engine.getId()));
        assertThat(engineHib.getName(), is(name));
        assertThat(engineHib.getSerialNumber(), is(sn));

        String newName = "V12";
        String newSn = "V121212";
        engine.setName(newName);
        engine.setSerialNumber(newSn);
        engine = er.update(engine);

        assertThat(engineHib.equals(engine), is(false));
        assertThat(engineHib.getId(), is(engine.getId()));
        assertThat(engineHib.getName().equals(newName), is(false));
        assertThat(engineHib.getSerialNumber().equals(newSn), is(false));
        assertThat(engine.getName().equals(newName), is(true));
        assertThat(engine.getSerialNumber().equals(newSn), is(true));
    }

    @Test
    public void whenNoEngineWithSuchIdThenTrue() {
        assertThat(er.findAll().size(), is(0));
        Engine engine = er.findById(1000);
        assertThat(engine.getId(), is(-1));
    }

    @Test
    public void findAllTest() {
        Engine[] engines = new Engine[]{
                new Engine("V6", "V666666"),
                new Engine("V12", "V121212"),
                new Engine("ZMZ409", "ZMZ9999")
        };
        Arrays.asList(engines).forEach(this.er::add);
        assertThat(this.er.findAll().size(), is(engines.length));
        assertThat(this.er.findAll(), containsInAnyOrder(engines));
    }

    @Test
    public void whenDeletesEngineFromDbThenDbHasntIt() {
        String name = "V6";
        String sn = "V666666";
        Engine engine = new Engine(name, sn);
        engine = er.add(engine);
        Engine engineHib = er.findById(engine.getId());
        assertThat(engineHib.equals(engine), is(true));
        er.delete(engine.getId());
        engineHib = er.findById(engine.getId());
        assertThat(engineHib.getId(), is(-1));
    }

    @Test
    public void whenSearchesByNameThenFoundTransmissionsWithThatName() {
        Engine[] engines = new Engine[]{
                new Engine("V6", "V666666"),
                new Engine("V6", "V666667"),
                new Engine("V12", "V121212"),
                new Engine("V12", "V131313"),
                new Engine("V12", "V141414")
        };
        Arrays.asList(engines).forEach(this.er::add);
        String searchName = "V6";
        List<Engine> engineSearch = this.er.findEnginesByName(searchName);
        assertThat(engineSearch.size(), is(2));
        assertThat(engineSearch, containsInAnyOrder(engines[0], engines[1]));
    }

    @Test
    public void whenSearchesBySnThenFoundTransmissionWithThatSn() {
        Engine[] engines = new Engine[]{
                new Engine("V6", "V666666"),
                new Engine("V6", "V666667"),
                new Engine("V12", "V121212"),
                new Engine("V12", "V131313"),
                new Engine("V12", "V141414")
        };
        Arrays.asList(engines).forEach(this.er::add);
        String searchSn = "V121212";
        Engine engineSearch = this.er.findEngineBySerialNumber(searchSn);
        assertThat(engineSearch, is(engines[2]));
    }
}