/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package youling.studio.protocol;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class ReadRequest extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2034235242074209000L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ReadRequest\",\"namespace\":\"youling.studio.protocol\",\"fields\":[{\"name\":\"filename\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<ReadRequest> ENCODER =
      new BinaryMessageEncoder<ReadRequest>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<ReadRequest> DECODER =
      new BinaryMessageDecoder<ReadRequest>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<ReadRequest> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<ReadRequest> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<ReadRequest>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this ReadRequest to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a ReadRequest from a ByteBuffer. */
  public static ReadRequest fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence filename;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public ReadRequest() {}

  /**
   * All-args constructor.
   * @param filename The new value for filename
   */
  public ReadRequest(java.lang.CharSequence filename) {
    this.filename = filename;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return filename;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: filename = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'filename' field.
   * @return The value of the 'filename' field.
   */
  public java.lang.CharSequence getFilename() {
    return filename;
  }

  /**
   * Sets the value of the 'filename' field.
   * @param value the value to set.
   */
  public void setFilename(java.lang.CharSequence value) {
    this.filename = value;
  }

  /**
   * Creates a new ReadRequest RecordBuilder.
   * @return A new ReadRequest RecordBuilder
   */
  public static youling.studio.protocol.ReadRequest.Builder newBuilder() {
    return new youling.studio.protocol.ReadRequest.Builder();
  }

  /**
   * Creates a new ReadRequest RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ReadRequest RecordBuilder
   */
  public static youling.studio.protocol.ReadRequest.Builder newBuilder(youling.studio.protocol.ReadRequest.Builder other) {
    return new youling.studio.protocol.ReadRequest.Builder(other);
  }

  /**
   * Creates a new ReadRequest RecordBuilder by copying an existing ReadRequest instance.
   * @param other The existing instance to copy.
   * @return A new ReadRequest RecordBuilder
   */
  public static youling.studio.protocol.ReadRequest.Builder newBuilder(youling.studio.protocol.ReadRequest other) {
    return new youling.studio.protocol.ReadRequest.Builder(other);
  }

  /**
   * RecordBuilder for ReadRequest instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ReadRequest>
    implements org.apache.avro.data.RecordBuilder<ReadRequest> {

    private java.lang.CharSequence filename;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(youling.studio.protocol.ReadRequest.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.filename)) {
        this.filename = data().deepCopy(fields()[0].schema(), other.filename);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing ReadRequest instance
     * @param other The existing instance to copy.
     */
    private Builder(youling.studio.protocol.ReadRequest other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.filename)) {
        this.filename = data().deepCopy(fields()[0].schema(), other.filename);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'filename' field.
      * @return The value.
      */
    public java.lang.CharSequence getFilename() {
      return filename;
    }

    /**
      * Sets the value of the 'filename' field.
      * @param value The value of 'filename'.
      * @return This builder.
      */
    public youling.studio.protocol.ReadRequest.Builder setFilename(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.filename = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'filename' field has been set.
      * @return True if the 'filename' field has been set, false otherwise.
      */
    public boolean hasFilename() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'filename' field.
      * @return This builder.
      */
    public youling.studio.protocol.ReadRequest.Builder clearFilename() {
      filename = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ReadRequest build() {
      try {
        ReadRequest record = new ReadRequest();
        record.filename = fieldSetFlags()[0] ? this.filename : (java.lang.CharSequence) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<ReadRequest>
    WRITER$ = (org.apache.avro.io.DatumWriter<ReadRequest>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<ReadRequest>
    READER$ = (org.apache.avro.io.DatumReader<ReadRequest>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
