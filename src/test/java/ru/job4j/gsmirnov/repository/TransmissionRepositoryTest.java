package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.gsmirnov.models.Transmission;

import java.util.Arrays;
import java.util.List;;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class TransmissionRepositoryTest {
    private final static Logger LOG = LogManager.getLogger(TransmissionRepositoryTest.class);

    private final TransmissionRepository tr = new TransmissionRepository();

    @Before
    public void init() {
        tr.deleteAll();
    }

    @Test
    public void whenAddTransmissionToDbThenDbHasIt() {
        String name = "Variator";
        String sn = "VAR55556";
        Transmission ts = new Transmission(name, sn);
        ts = tr.add(ts);
        Transmission tsHib = tr.findById(ts.getId());
        assertThat(tsHib.equals(ts), is(true));
        assertThat(tsHib.getId(), is(ts.getId()));
        assertThat(tsHib.getName(), is(name));
        assertThat(tsHib.getSerialNumber(), is(sn));
    }

    @Test
    public void whenUpdatesTransmissionInDbThenDbHasChanges() {
        String name = "Variator";
        String sn = "VAR55556";
        Transmission ts = new Transmission(name, sn);
        ts = tr.add(ts);
        Transmission tsHib = tr.findById(ts.getId());
        assertThat(tsHib.equals(ts), is(true));
        assertThat(tsHib.getId(), is(ts.getId()));
        assertThat(tsHib.getName(), is(name));
        assertThat(tsHib.getSerialNumber(), is(sn));

        String newName = "AKPP";
        String newSn = "AKPP66666";
        ts.setName(newName);
        ts.setSerialNumber(newSn);
        ts = tr.update(ts);

        assertThat(tsHib.equals(ts), is(false));
        assertThat(tsHib.getId(), is(ts.getId()));
        assertThat(tsHib.getName().equals(newName), is(false));
        assertThat(tsHib.getSerialNumber().equals(newSn), is(false));
        assertThat(ts.getName().equals(newName), is(true));
        assertThat(ts.getSerialNumber().equals(newSn), is(true));
    }

    @Test
    public void whenNoTransmissionWithSuchIdThenTrue() {
        assertThat(tr.findAll().size(), is(0));
        Transmission ts = tr.findById(1000);
        assertThat(ts.getId(), is(-1));
    }

    @Test
    public void findAllTest() {
        Transmission[] tss = new Transmission[]{
                new Transmission("AKPP-1", "A111111"),
                new Transmission("AKPP-2", "A222222"),
                new Transmission("AKPP-3", "A333333")
        };
        Arrays.asList(tss).forEach(this.tr::add);
        assertThat(this.tr.findAll().size(), is(tss.length));
        assertThat(this.tr.findAll(), containsInAnyOrder(tss));
    }

    @Test
    public void whenDeletesTransmissionFromDbThenDbHasntIt() {
        String name = "Variator";
        String sn = "VAR55556";
        Transmission ts = new Transmission(name, sn);
        ts = tr.add(ts);
        Transmission tsHib = tr.findById(ts.getId());
        assertThat(tsHib.equals(ts), is(true));
        tr.delete(ts.getId());
        tsHib = tr.findById(ts.getId());
        assertThat(tsHib.getId(), is(-1));
    }

    @Test
    public void whenSearchesByNameThenFoundTransmissionsWithThatName() {
        Transmission[] tss = new Transmission[]{
                new Transmission("AKPP", "A111111"),
                new Transmission("AKPP", "A222222"),
                new Transmission("MKPP", "M111111"),
                new Transmission("MKPP", "M222222"),
                new Transmission("MKPP", "M333333")
        };
        Arrays.asList(tss).forEach(this.tr::add);
        String searchName = "AKPP";
        List<Transmission> tsSearch = this.tr.findTransmissionsByName(searchName);
        assertThat(tsSearch.size(), is(2));
        assertThat(tsSearch, containsInAnyOrder(tss[0], tss[1]));
    }

    @Test
    public void whenSearchesBySnThenFoundTransmissionWithThatSn() {
        Transmission[] tss = new Transmission[]{
                new Transmission("AKPP", "A111111"),
                new Transmission("AKPP", "A222222"),
                new Transmission("MKPP", "M111111"),
                new Transmission("MKPP", "M222222"),
                new Transmission("MKPP", "M333333")
        };
        Arrays.asList(tss).forEach(this.tr::add);
        String searchSn = "M111111";
        Transmission tsSearch = this.tr.findTransmissionBySerialNumber(searchSn);
        assertThat(tsSearch, is(tss[2]));
    }
}