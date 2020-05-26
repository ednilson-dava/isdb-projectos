package com.ednilsondava.isdb.relatorio;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

@RequestScoped
public class Relatorio {
    @Resource(name = "jdbc/avaldocente")
    private DataSource dataSource;

    public void gerarRelatorioPDF(String jrxml, Map<String, Object> parametros, OutputStream saida) throws JRException, SQLException {
        JasperReport report = JasperCompileManager.compileReport(jrxml);
        JasperPrint print = JasperFillManager.fillReport(report, parametros, dataSource.getConnection());
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(saida));

        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }
}
