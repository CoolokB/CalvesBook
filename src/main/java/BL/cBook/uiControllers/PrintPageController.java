package BL.cBook.uiControllers;

import BL.cBook.services.MyControllerWithParameter;
import BL.cBook.utils.MyPrinter;
import javafx.print.PageOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.util.Arrays;
import java.util.List;

public class PrintPageController implements MyControllerWithParameter<ParametersContainer> {

    public Pane pane;
    public Button button;

    public Label a1;
    public Label a2;
    public Label a3;
    public Label a4;
    public Label a5;
    public Label a6;

    public Label b1;
    public Label b2;
    public Label b3;
    public Label b4;
    public Label b5;
    public Label b6;

    public Label c1;
    public Label c2;
    public Label c3;
    public Label c4;
    public Label c5;
    public Label c6;

    public Label d1;
    public Label d2;
    public Label d3;
    public Label d4;
    public Label d5;
    public Label d6;

    public Label e1;
    public Label e2;
    public Label e3;
    public Label e4;
    public Label e5;
    public Label e6;

    public Label f1;
    public Label f2;
    public Label f3;
    public Label f4;
    public Label f5;
    public Label f6;

    public Label g1;
    public Label g2;
    public Label g3;
    public Label g4;
    public Label g5;
    public Label g6;

    public Label h1;
    public Label h2;
    public Label h3;
    public Label h4;
    public Label h5;
    public Label h6;

    public Label i1;
    public Label i2;
    public Label i3;
    public Label i4;
    public Label i5;
    public Label i6;

    public Label j1;
    public Label j2;
    public Label j3;
    public Label j4;
    public Label j5;
    public Label j6;

    public Label k1;
    public Label k2;
    public Label k3;
    public Label k4;
    public Label k5;
    public Label k6;

    public Label l1;
    public Label l2;
    public Label l3;
    public Label l4;
    public Label l5;
    public Label l6;

    public Label m1;
    public Label m2;
    public Label m3;
    public Label m4;
    public Label m5;
    public Label m6;

    public Label n1;
    public Label n2;
    public Label n3;
    public Label n4;
    public Label n5;
    public Label n6;

    public Label o1;
    public Label o2;
    public Label o3;
    public Label o4;
    public Label o5;
    public Label o6;

    public Label p1;
    public Label p2;
    public Label p3;
    public Label p4;
    public Label p5;
    public Label p6;

    public Label q1;
    public Label q2;
    public Label q3;
    public Label q4;
    public Label q5;
    public Label q6;

    public Label r1;
    public Label r2;
    public Label r3;
    public Label r4;
    public Label r5;
    public Label r6;

    public Label s1;
    public Label s2;
    public Label s3;
    public Label s4;
    public Label s5;
    public Label s6;

    public Label t1;
    public Label t2;
    public Label t3;
    public Label t4;
    public Label t5;
    public Label t6;

    public Label u1;
    public Label u2;
    public Label u3;
    public Label u4;
    public Label u5;
    public Label u6;

    public Label v1;
    public Label v2;
    public Label v3;
    public Label v4;
    public Label v5;
    public Label v6;

    public Label w1;
    public Label w2;
    public Label w3;
    public Label w4;
    public Label w5;
    public Label w6;

    public Label x1;
    public Label x2;
    public Label x3;
    public Label x4;
    public Label x5;
    public Label x6;

    public Label y1;
    public Label y2;
    public Label y3;
    public Label y4;
    public Label y5;
    public Label y6;

    public Label z1;
    public Label z2;
    public Label z3;
    public Label z4;
    public Label z5;
    public Label z6;

    public Label aa1;
    public Label aa2;
    public Label aa3;
    public Label aa4;
    public Label aa5;
    public Label aa6;

    public Label ba1;
    public Label ba2;
    public Label ba3;
    public Label ba4;
    public Label ba5;
    public Label ba6;

    public Label ca1;
    public Label ca2;
    public Label ca3;
    public Label ca4;
    public Label ca5;
    public Label ca6;

    public Label da1;
    public Label da2;
    public Label da3;
    public Label da4;
    public Label da5;
    public Label da6;

    public Label ea1;
    public Label ea2;
    public Label ea3;
    public Label ea4;
    public Label ea5;
    public Label ea6;

    public Label fa1;
    public Label fa2;
    public Label fa3;
    public Label fa4;
    public Label fa5;
    public Label fa6;

    public Label ga1;
    public Label ga2;
    public Label ga3;
    public Label ga4;
    public Label ga5;
    public Label ga6;

    public Label ha1;
    public Label ha2;
    public Label ha3;
    public Label ha4;
    public Label ha5;
    public Label ha6;

    public Label ia1;
    public Label ia2;
    public Label ia3;
    public Label ia4;
    public Label ia5;
    public Label ia6;

    public Label ja1;
    public Label ja2;
    public Label ja3;
    public Label ja4;
    public Label ja5;
    public Label ja6;

    public Label ka1;
    public Label ka2;
    public Label ka3;
    public Label ka4;
    public Label ka5;
    public Label ka6;

    public Label la1;
    public Label la2;
    public Label la3;
    public Label la4;
    public Label la5;
    public Label la6;

    public Label ma1;
    public Label ma2;
    public Label ma3;
    public Label ma4;
    public Label ma5;
    public Label ma6;

    public Label na1;
    public Label na2;
    public Label na3;
    public Label na4;
    public Label na5;
    public Label na6;

    public Label oa1;
    public Label oa2;
    public Label oa3;
    public Label oa4;
    public Label oa5;
    public Label oa6;

    public Label pa1;
    public Label pa2;
    public Label pa3;
    public Label pa4;
    public Label pa5;
    public Label pa6;

    public Label qa1;
    public Label qa2;
    public Label qa3;
    public Label qa4;
    public Label qa5;
    public Label qa6;

    public Label ra1;
    public Label ra2;
    public Label ra3;
    public Label ra4;
    public Label ra5;
    public Label ra6;

    public Label sa1;
    public Label sa2;
    public Label sa3;
    public Label sa4;
    public Label sa5;
    public Label sa6;

    public Label ta1;
    public Label ta2;
    public Label ta3;
    public Label ta4;
    public Label ta5;
    public Label ta6;

    public Label ua1;
    public Label ua2;
    public Label ua3;
    public Label ua4;
    public Label ua5;
    public Label ua6;

    @Override
    public void initialize(MainController mc, ParametersContainer container) {

        List<List<String>> rowsList = container.getRowsList();
        List<String> firstLabel = container.getLabelList();

        makePane(firstLabel, rowsList);
        button.setOnAction(event -> MyPrinter.print(pane, button.getScene(), PageOrientation.PORTRAIT));

    }

    private static void initLabels(List<List<Label>> l, List<List<String>> s) {

        for (int i = 0; i < s.size(); i++) {
            setCells(l.get(i), s.get(i));
        }
    }

    private static void setCells(List<Label> list, List<String> string) {

        for (int i = 0; i < string.size(); i++) {
            list.get(i).setText(string.get(i));
        }
    }

    private void makePane(List firstLabel, List rows) {

        List<Label> a = Arrays.asList(a1, a2, a3, a4, a5, a6);
        List<Label> b = Arrays.asList(b1, b2, b3, b4, b5, b6);
        List<Label> c = Arrays.asList(c1, c2, c3, c4, c5, c6);
        List<Label> d = Arrays.asList(d1, d2, d3, d4, d5, d6);
        List<Label> e = Arrays.asList(e1, e2, e3, e4, e5, e6);
        List<Label> f = Arrays.asList(f1, f2, f3, f4, f5, f6);
        List<Label> g = Arrays.asList(g1, g2, g3, g4, g5, g6);
        List<Label> h = Arrays.asList(h1, h2, h3, h4, h5, h6);
        List<Label> i = Arrays.asList(i1, i2, i3, i4, i5, i6);
        List<Label> j = Arrays.asList(j1, j2, j3, j4, j5, j6);
        List<Label> k = Arrays.asList(k1, k2, k3, k4, k5, k6);
        List<Label> l = Arrays.asList(l1, l2, l3, l4, l5, l6);
        List<Label> m = Arrays.asList(m1, m2, m3, m4, m5, m6);
        List<Label> n = Arrays.asList(n1, n2, n3, n4, n5, n6);
        List<Label> o = Arrays.asList(o1, o2, o3, o4, o5, o6);
        List<Label> p = Arrays.asList(p1, p2, p3, p4, p5, p6);
        List<Label> q = Arrays.asList(q1, q2, q3, q4, q5, q6);
        List<Label> r = Arrays.asList(r1, r2, r3, r4, r5, r6);
        List<Label> s = Arrays.asList(s1, s2, s3, s4, s5, s6);
        List<Label> t = Arrays.asList(t1, t2, t3, t4, t5, t6);
        List<Label> u = Arrays.asList(u1, u2, u3, u4, u5, u6);
        List<Label> v = Arrays.asList(v1, v2, v3, v4, v5, v6);
        List<Label> w = Arrays.asList(w1, w2, w3, w4, w5, w6);
        List<Label> x = Arrays.asList(x1, x2, x3, x4, x5, x6);
        List<Label> y = Arrays.asList(y1, y2, y3, y4, y5, y6);
        List<Label> z = Arrays.asList(z1, z2, z3, z4, z5, z6);

        List<Label> aa = Arrays.asList(aa1, aa2, aa3, aa4, aa5, aa6);
        List<Label> ba = Arrays.asList(ba1, ba2, ba3, ba4, ba5, ba6);
        List<Label> ca = Arrays.asList(ca1, ca2, ca3, ca4, ca5, ca6);
        List<Label> da = Arrays.asList(da1, da2, da3, da4, da5, da6);
        List<Label> ea = Arrays.asList(ea1, ea2, ea3, ea4, ea5, ea6);
        List<Label> fa = Arrays.asList(fa1, fa2, fa3, fa4, fa5, fa6);
        List<Label> ga = Arrays.asList(ga1, ga2, ga3, ga4, ga5, ga6);
        List<Label> ha = Arrays.asList(ha1, ha2, ha3, ha4, ha5, ha6);
        List<Label> ia = Arrays.asList(ia1, ia2, ia3, ia4, ia5, ia6);
        List<Label> ja = Arrays.asList(ja1, ja2, ja3, ja4, ja5, ja6);
        List<Label> ka = Arrays.asList(ka1, ka2, ka3, ka4, ka5, ka6);
        List<Label> la = Arrays.asList(la1, la2, la3, la4, la5, la6);
        List<Label> ma = Arrays.asList(ma1, ma2, ma3, ma4, ma5, ma6);
        List<Label> na = Arrays.asList(na1, na2, na3, na4, na5, na6);
        List<Label> oa = Arrays.asList(oa1, oa2, oa3, oa4, oa5, oa6);
        List<Label> pa = Arrays.asList(pa1, pa2, pa3, pa4, pa5, pa6);
        List<Label> qa = Arrays.asList(qa1, qa2, qa3, qa4, qa5, qa6);
        List<Label> ra = Arrays.asList(ra1, ra2, ra3, ra4, ra5, ra6);
        List<Label> sa = Arrays.asList(sa1, sa2, sa3, sa4, sa5, sa6);
        List<Label> ta = Arrays.asList(ta1, ta2, ta3, ta4, ta5, ta6);
        List<Label> ua = Arrays.asList(ua1, ua2, ua3, ua4, ua5, ua6);

        setMatrix(firstLabel, rows, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z,
                aa, ba, ca, da, ea, fa, ga, ha, ia, ja, ka, la, ma, na, oa, pa, qa, ra, sa, ta, ua);

    }



    private void setMatrix(List firstLabel, List rows, List<Label> a, List<Label> b, List<Label> c, List<Label> d, List<Label> e, List<Label> f, List<Label> g, List<Label> h,
                           List<Label> i, List<Label> j, List<Label> k, List<Label> l, List<Label> m, List<Label> n, List<Label> o, List<Label> p, List<Label> q, List<Label> r,
                           List<Label> s, List<Label> t, List<Label> u, List<Label> v, List<Label> w, List<Label> x, List<Label> y, List<Label> z, List<Label> aa, List<Label> ba,
                           List<Label> ca, List<Label> da, List<Label> ea, List<Label> fa, List<Label> ga, List<Label> ha, List<Label> ia, List<Label> ja, List<Label> ka, List<Label> la,
                           List<Label> ma, List<Label> na, List<Label> oa, List<Label> pa, List<Label> qa, List<Label> ra, List<Label> sa, List<Label> ta, List<Label> ua) {
        List<List<Label>> matrix = Arrays.asList(

                b, c, d, e, f, g, h, i, j,
                k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z,

                aa, ba, ca, da, ea, fa, ga, ha, ia, ja,
                ka, la, ma, na, oa, pa, qa, ra, sa, ta, ua

        );

        setCells(a, firstLabel);
        initLabels(matrix, rows);
    }
}
