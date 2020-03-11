package BL.cBook.utils;

import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyPrinter {

    public static void print(Pane p, Scene scene, PageOrientation orientation) {

        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, orientation, Printer.MarginType.HARDWARE_MINIMUM);

        Stage stage = (Stage) scene.getWindow();
        PrinterJob job = PrinterJob.createPrinterJob();
        job.getJobSettings().setPageLayout(pageLayout);

        // job.showPageSetupDialog(stage);
        // boolean procced = job.showPrintDialog(stage);

        boolean proceed = job.showPageSetupDialog(stage);
        boolean printed = job.printPage(p);
        if (proceed) {
            if (printed) {
                job.endJob();
            }
        }
    }

//    public static void print(Pane p, Pane p2, Scene scene, PageOrientation orientation) {
//
//        Printer printer = Printer.getDefaultPrinter();
//        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, orientation, Printer.MarginType.HARDWARE_MINIMUM);
//
//        Stage stage = (Stage) scene.getWindow();
//        PrinterJob job = PrinterJob.createPrinterJob();
//        job.getJobSettings().setPageLayout(pageLayout);
//
//        boolean proceed = job.showPageSetupDialog(stage);
//        boolean printed = job.printPage(p);
//        boolean printed1 = job.printPage(p2);
//
//        if (proceed) {
//            if (printed && printed1) {
//                job.endJob();
//            }
//
//        }
//    }
//
//    public static void print(Pane p, Pane p2, Pane p3, Scene scene, PageOrientation orientation) {
//
//        Printer printer = Printer.getDefaultPrinter();
//        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, orientation, Printer.MarginType.HARDWARE_MINIMUM);
//
//        Stage stage = (Stage) scene.getWindow();
//        PrinterJob job = PrinterJob.createPrinterJob();
//        job.getJobSettings().setPageLayout(pageLayout);
//
//        boolean proceed = job.showPageSetupDialog(stage);
//        boolean printed = job.printPage(p);
//        boolean printed1 = job.printPage(p2);
//        boolean printed2 = job.printPage(p3);
//
//        if (proceed) {
//            if (printed && printed1 && printed2) {
//                job.endJob();
//            }
//        }
//    }
//
//    public static void print(Pane p, Pane p2, Pane p3, Pane p4, Scene scene, PageOrientation orientation) {
//
//        Printer printer = Printer.getDefaultPrinter();
//        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, orientation, Printer.MarginType.HARDWARE_MINIMUM);
//
//        Stage stage = (Stage) scene.getWindow();
//        PrinterJob job = PrinterJob.createPrinterJob();
//        job.getJobSettings().setPageLayout(pageLayout);
//
//        boolean proceed = job.showPageSetupDialog(stage);
//        boolean printed = job.printPage(p);
//        boolean printed1 = job.printPage(p2);
//        boolean printed2 = job.printPage(p3);
//        boolean printed3 = job.printPage(p4);
//
//        if (proceed) {
//            if (printed && printed1 && printed2 && printed3) {
//                job.endJob();
//            }
//        }
//    }
//
//    public static void print(Pane p, Pane p2, Pane p3, Pane p4, Pane p5, Scene scene, PageOrientation orientation) {
//
//        Printer printer = Printer.getDefaultPrinter();
//        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, orientation, Printer.MarginType.HARDWARE_MINIMUM);
//
//        Stage stage = (Stage) scene.getWindow();
//        PrinterJob job = PrinterJob.createPrinterJob();
//        job.getJobSettings().setPageLayout(pageLayout);
//
//        boolean proceed = job.showPageSetupDialog(stage);
//        boolean printed = job.printPage(p);
//        boolean printed1 = job.printPage(p2);
//        boolean printed2 = job.printPage(p3);
//        boolean printed3 = job.printPage(p4);
//        boolean printed4 = job.printPage(p5);
//
//        if (proceed) {
//            if (printed && printed1 && printed2 && printed3 && printed4) {
//                job.endJob();
//            }
//        }
//    }
//
//    public static void print(Pane p, Pane p2, Pane p3, Pane p4, Pane p5, Pane p6, Scene scene, PageOrientation orientation) {
//
//        Printer printer = Printer.getDefaultPrinter();
//        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, orientation, Printer.MarginType.HARDWARE_MINIMUM);
//
//        Stage stage = (Stage) scene.getWindow();
//        PrinterJob job = PrinterJob.createPrinterJob();
//        job.getJobSettings().setPageLayout(pageLayout);
//
//        boolean proceed = job.showPageSetupDialog(stage);
//        boolean printed = job.printPage(p);
//        boolean printed1 = job.printPage(p2);
//        boolean printed2 = job.printPage(p3);
//        boolean printed3 = job.printPage(p4);
//        boolean printed4 = job.printPage(p5);
//        boolean printed5 = job.printPage(p6);
//
//        if (proceed) {
//            if (printed && printed1 && printed2 && printed3 && printed4 && printed5) {
//                job.endJob();
//            }
//        }
//    }
//
//    public static void print(Pane p, Pane p2, Pane p3, Pane p4, Pane p5, Pane p6, Pane p7, Scene scene, PageOrientation orientation) {
//
//        Printer printer = Printer.getDefaultPrinter();
//        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, orientation, Printer.MarginType.HARDWARE_MINIMUM);
//
//        Stage stage = (Stage) scene.getWindow();
//        PrinterJob job = PrinterJob.createPrinterJob();
//        job.getJobSettings().setPageLayout(pageLayout);
//
//        boolean proceed = job.showPageSetupDialog(stage);
//        boolean printed = job.printPage(p);
//        boolean printed1 = job.printPage(p2);
//        boolean printed2 = job.printPage(p3);
//        boolean printed3 = job.printPage(p4);
//        boolean printed4 = job.printPage(p5);
//        boolean printed5 = job.printPage(p6);
//        boolean printed6 = job.printPage(p7);
//
//        if (proceed) {
//            if (printed && printed1 && printed2 && printed3 && printed4 && printed5 && printed6) {
//                job.endJob();
//            }
//        }
//    }
}