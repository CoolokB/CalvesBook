package BL.cBook.uiControllers;

import BL.cBook.services.MyControllerWithParameter;
import BL.cBook.utils.MyPrinter;
import javafx.print.PageOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.util.Arrays;
import java.util.List;

public class PrintPage2Controller implements MyControllerWithParameter<ParametersContainer> {

    public Pane pane;
    public Button button;

    public Label a1;
    public Label a2;
    public Label a3;
    public Label a4;
    public Label a5;
    public Label a6;
    public Label a7;
    public Label a8;
    public Label a9;
    public Label a10;

    public Label b1;
    public Label b2;
    public Label b3;
    public Label b4;
    public Label b5;
    public Label b6;
    public Label b7;
    public Label b8;
    public Label b9;
    public Label b10;

    public Label c1;
    public Label c2;
    public Label c3;
    public Label c4;
    public Label c5;
    public Label c6;
    public Label c7;
    public Label c8;
    public Label c9;
    public Label c10;

    public Label d1;
    public Label d2;
    public Label d3;
    public Label d4;
    public Label d5;
    public Label d6;
    public Label d7;
    public Label d8;
    public Label d9;
    public Label d10;

    public Label e1;
    public Label e2;
    public Label e3;
    public Label e4;
    public Label e5;
    public Label e6;
    public Label e7;
    public Label e8;
    public Label e9;
    public Label e10;

    public Label f1;
    public Label f2;
    public Label f3;
    public Label f4;
    public Label f5;
    public Label f6;
    public Label f7;
    public Label f8;
    public Label f9;
    public Label f10;

    public Label g1;
    public Label g2;
    public Label g3;
    public Label g4;
    public Label g5;
    public Label g6;
    public Label g7;
    public Label g8;
    public Label g9;
    public Label g10;

    public Label h1;
    public Label h2;
    public Label h3;
    public Label h4;
    public Label h5;
    public Label h6;
    public Label h7;
    public Label h8;
    public Label h9;
    public Label h10;

    public Label i1;
    public Label i2;
    public Label i3;
    public Label i4;
    public Label i5;
    public Label i6;
    public Label i7;
    public Label i8;
    public Label i9;
    public Label i10;

    public Label j1;
    public Label j2;
    public Label j3;
    public Label j4;
    public Label j5;
    public Label j6;
    public Label j7;
    public Label j8;
    public Label j9;
    public Label j10;

    public Label k1;
    public Label k2;
    public Label k3;
    public Label k4;
    public Label k5;
    public Label k6;
    public Label k7;
    public Label k8;
    public Label k9;
    public Label k10;

    public Label l1;
    public Label l2;
    public Label l3;
    public Label l4;
    public Label l5;
    public Label l6;
    public Label l7;
    public Label l8;
    public Label l9;
    public Label l10;

    public Label m1;
    public Label m2;
    public Label m3;
    public Label m4;
    public Label m5;
    public Label m6;
    public Label m7;
    public Label m8;
    public Label m9;
    public Label m10;

    public Label n1;
    public Label n2;
    public Label n3;
    public Label n4;
    public Label n5;
    public Label n6;
    public Label n7;
    public Label n8;
    public Label n9;
    public Label n10;

    public Label o1;
    public Label o2;
    public Label o3;
    public Label o4;
    public Label o5;
    public Label o6;
    public Label o7;
    public Label o8;
    public Label o9;
    public Label o10;

    public Label p1;
    public Label p2;
    public Label p3;
    public Label p4;
    public Label p5;
    public Label p6;
    public Label p7;
    public Label p8;
    public Label p9;
    public Label p10;

    public Label q1;
    public Label q2;
    public Label q3;
    public Label q4;
    public Label q5;
    public Label q6;
    public Label q7;
    public Label q8;
    public Label q9;
    public Label q10;

    public Label r1;
    public Label r2;
    public Label r3;
    public Label r4;
    public Label r5;
    public Label r6;
    public Label r7;
    public Label r8;
    public Label r9;
    public Label r10;

    public Label s1;
    public Label s2;
    public Label s3;
    public Label s4;
    public Label s5;
    public Label s6;
    public Label s7;
    public Label s8;
    public Label s9;
    public Label s10;

    public Label t1;
    public Label t2;
    public Label t3;
    public Label t4;
    public Label t5;
    public Label t6;
    public Label t7;
    public Label t8;
    public Label t9;
    public Label t10;

    public Label u1;
    public Label u2;
    public Label u3;
    public Label u4;
    public Label u5;
    public Label u6;
    public Label u7;
    public Label u8;
    public Label u9;
    public Label u10;

    public Label v1;
    public Label v2;
    public Label v3;
    public Label v4;
    public Label v5;
    public Label v6;
    public Label v7;
    public Label v8;
    public Label v9;
    public Label v10;

    public Label w1;
    public Label w2;
    public Label w3;
    public Label w4;
    public Label w5;
    public Label w6;
    public Label w7;
    public Label w8;
    public Label w9;
    public Label w10;

    public Label x1;
    public Label x2;
    public Label x3;
    public Label x4;
    public Label x5;
    public Label x6;
    public Label x7;
    public Label x8;
    public Label x9;
    public Label x10;

    public Label y1;
    public Label y2;
    public Label y3;
    public Label y4;
    public Label y5;
    public Label y6;
    public Label y7;
    public Label y8;
    public Label y9;
    public Label y10;

    public Label z1;
    public Label z2;
    public Label z3;
    public Label z4;
    public Label z5;
    public Label z6;
    public Label z7;
    public Label z8;
    public Label z9;
    public Label z10;

    public Label aa1;
    public Label aa2;
    public Label aa3;
    public Label aa4;
    public Label aa5;
    public Label aa6;
    public Label aa7;
    public Label aa8;
    public Label aa9;
    public Label aa10;

    public Label ba1;
    public Label ba2;
    public Label ba3;
    public Label ba4;
    public Label ba5;
    public Label ba6;
    public Label ba7;
    public Label ba8;
    public Label ba9;
    public Label ba10;

    public Label ca1;
    public Label ca2;
    public Label ca3;
    public Label ca4;
    public Label ca5;
    public Label ca6;
    public Label ca7;
    public Label ca8;
    public Label ca9;
    public Label ca10;

    public Label da1;
    public Label da2;
    public Label da3;
    public Label da4;
    public Label da5;
    public Label da6;
    public Label da7;
    public Label da8;
    public Label da9;
    public Label da10;

    public Label ea1;
    public Label ea2;
    public Label ea3;
    public Label ea4;
    public Label ea5;
    public Label ea6;
    public Label ea7;
    public Label ea8;
    public Label ea9;
    public Label ea10;

    public Label fa1;
    public Label fa2;
    public Label fa3;
    public Label fa4;
    public Label fa5;
    public Label fa6;
    public Label fa7;
    public Label fa8;
    public Label fa9;
    public Label fa10;


    @Override
    public void initialize(MainController mc,ParametersContainer container) {



        List<List<String>> rowsList = container.getRowsList();
        List<String> firstLabel = container.getLabelList();

        makePane1(firstLabel, rowsList);
        button.setOnAction(event -> MyPrinter.print(pane, button.getScene(), PageOrientation.LANDSCAPE));

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

    private void makePane1(List firstLabel, List rows) {

        List<Label> a = Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
        List<Label> b = Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);
        List<Label> c = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
        List<Label> d = Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10);
        List<Label> e = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
        List<Label> f = Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10);
        List<Label> g = Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10);
        List<Label> h = Arrays.asList(h1, h2, h3, h4, h5, h6, h7, h8, h9, h10);
        List<Label> i = Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10);
        List<Label> j = Arrays.asList(j1, j2, j3, j4, j5, j6, j7, j8, j9, j10);
        List<Label> k = Arrays.asList(k1, k2, k3, k4, k5, k6, k7, k8, k9, k10);
        List<Label> l = Arrays.asList(l1, l2, l3, l4, l5, l6, l7, l8, l9, l10);
        List<Label> m = Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10);
        List<Label> n = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10);
        List<Label> o = Arrays.asList(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10);
        List<Label> p = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        List<Label> q = Arrays.asList(q1, q2, q3, q4, q5, q6, q7, q8, q9, q10);
        List<Label> r = Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
        List<Label> s = Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
        List<Label> t = Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
        List<Label> u = Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10);
        List<Label> v = Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);
        List<Label> w = Arrays.asList(w1, w2, w3, w4, w5, w6, w7, w8, w9, w10);
        List<Label> x = Arrays.asList(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10);
        List<Label> y = Arrays.asList(y1, y2, y3, y4, y5, y6, y7, y8, y9, y10);
        List<Label> z = Arrays.asList(z1, z2, z3, z4, z5, z6, z7, z8, z9, z10);
        List<Label> aa = Arrays.asList(aa1, aa2, aa3, aa4, aa5, aa6, aa7, aa8, aa9, aa10);
        List<Label> ba = Arrays.asList(ba1, ba2, ba3, ba4, ba5, ba6, ba7, ba8, ba9, ba10);
        List<Label> ca = Arrays.asList(ca1, ca2, ca3, ca4, ca5, ca6, ca7, ca8, ca9, ca10);
        List<Label> da = Arrays.asList(da1, da2, da3, da4, da5, da6, da7, da8, da9, da10);
        List<Label> ea = Arrays.asList(ea1, ea2, ea3, ea4, ea5, ea6, ea7, ea8, ea9, ea10);
        List<Label> fa = Arrays.asList(fa1, fa2, fa3, fa4, fa5, fa6, fa7, fa8, fa9, fa10);

        setMatrix(firstLabel, rows, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, aa, ba, ca, da, ea, fa);

    }



    private void setMatrix(List firstLabel, List rows, List<Label> a, List<Label> b, List<Label> c, List<Label> d, List<Label> e, List<Label> f,
                           List<Label> g, List<Label> h, List<Label> i, List<Label> j, List<Label> k, List<Label> l, List<Label> m, List<Label> n,
                           List<Label> o, List<Label> p, List<Label> q, List<Label> r, List<Label> s, List<Label> t, List<Label> u, List<Label> v,
                           List<Label> w, List<Label> x, List<Label> y, List<Label> z, List<Label> aa, List<Label> ba, List<Label> ca, List<Label> da,
                           List<Label> ea, List<Label> fa) {
        List<List<Label>> matrix = Arrays.asList(b, c, d, e, f, g, h, i, j,
                k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, aa, ba, ca, da, ea, fa);

        setCells(a, firstLabel);
        initLabels(matrix, rows);
    }
}
