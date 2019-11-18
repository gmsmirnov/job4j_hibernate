package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.gsmirnov.models.Body;

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
public class BodyRepositoryTest {
    private final static Logger LOG = LogManager.getLogger(BodyRepositoryTest.class);

    private final BodyRepository br = new BodyRepository();

    @Before
    public void init() {
        br.deleteAll();
    }

    @Test
    public void whenAddBodyToDbThenDbHasIt() {
        String name = "Sedan";
        String sn = "SED55556";
        Body body = new Body(name, sn);
        body = br.add(body);
        Body bodyHib = br.findById(body.getId());
        assertThat(bodyHib.equals(body), is(true));
        assertThat(bodyHib.getId(), is(body.getId()));
        assertThat(bodyHib.getName(), is(name));
        assertThat(bodyHib.getSerialNumber(), is(sn));
    }

    @Test
    public void whenUpdatesBodyInDbThenDbHasChanges() {
        String name = "Sedan";
        String sn = "SED55556";
        Body body = new Body(name, sn);
        body = br.add(body);
        Body bodyHib = br.findById(body.getId());
        assertThat(bodyHib.equals(body), is(true));
        assertThat(bodyHib.getId(), is(body.getId()));
        assertThat(bodyHib.getName(), is(name));
        assertThat(bodyHib.getSerialNumber(), is(sn));

        String newName = "Universal";
        String newSn = "UNI66666";
        body.setName(newName);
        body.setSerialNumber(newSn);
        body = br.update(body);

        assertThat(bodyHib.equals(body), is(false));
        assertThat(bodyHib.getId(), is(body.getId()));
        assertThat(bodyHib.getName().equals(newName), is(false));
        assertThat(bodyHib.getSerialNumber().equals(newSn), is(false));
        assertThat(body.getName().equals(newName), is(true));
        assertThat(body.getSerialNumber().equals(newSn), is(true));
    }

    @Test
    public void whenNoBodyWithSuchIdThenTrue() {
        assertThat(br.findAll().size(), is(0));
        Body ts = br.findById(1000);
        assertThat(ts.getId(), is(-1));
    }

    @Test
    public void findAllTest() {
        Body[] bodies = new Body[]{
                new Body("Sedan", "SED111111"),
                new Body("Sedan", "SED222222"),
                new Body("Sedan", "SED333333")
        };
        Arrays.asList(bodies).forEach(this.br::add);
        assertThat(this.br.findAll().size(), is(bodies.length));
        assertThat(this.br.findAll(), containsInAnyOrder(bodies));
    }

    @Test
    public void whenDeletesBodyFromDbThenDbHasntIt() {
        String name = "Sedan";
        String sn = "SED55556";
        Body body = new Body(name, sn);
        body = br.add(body);
        Body bodyHib = br.findById(body.getId());
        assertThat(bodyHib.equals(body), is(true));
        br.delete(body.getId());
        bodyHib = br.findById(body.getId());
        assertThat(bodyHib.getId(), is(-1));
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
        Arrays.asList(bodies).forEach(this.br::add);
        String searchName = "Sedan";
        List<Body> bodySearch = this.br.findBodyByName(searchName);
        assertThat(bodySearch.size(), is(2));
        assertThat(bodySearch, containsInAnyOrder(bodies[0], bodies[1]));
    }

    @Test
    public void whenSearchesBySnThenFoundBodyWithThatSn() {
        Body[] bodies = new Body[]{
                new Body("Sedan", "SED111111"),
                new Body("Sedan", "SED222222"),
                new Body("Universal", "UNI111111"),
                new Body("Universal", "UNI222222"),
                new Body("Universal", "UNI333333")
        };
        Arrays.asList(bodies).forEach(this.br::add);
        String searchSn = "UNI111111";
        Body bodySearch = this.br.findBodyBySerialNumber(searchSn);
        assertThat(bodySearch, is(bodies[2]));
    }
}