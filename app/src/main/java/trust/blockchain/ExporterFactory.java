package trust.blockchain;

public interface ExporterFactory {
    Exporter get(Slip slip);
}
