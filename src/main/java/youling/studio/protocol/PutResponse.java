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
public class PutResponse extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6452323486167527867L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PutResponse\",\"namespace\":\"youling.studio.protocol\",\"fields\":[{\"name\":\"status\",\"type\":\"int\"},{\"name\":\"msg\",\"type\":\"string\"},{\"name\":\"datanodes\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PutResponse> ENCODER =
      new BinaryMessageEncoder<PutResponse>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PutResponse> DECODER =
      new BinaryMessageDecoder<PutResponse>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<PutResponse> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<PutResponse> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PutResponse>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this PutResponse to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a PutResponse from a ByteBuffer. */
  public static PutResponse fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public int status;
  @Deprecated public java.lang.CharSequence msg;
  @Deprecated public java.util.List<java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> datanodes;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PutResponse() {}

  /**
   * All-args constructor.
   * @param status The new value for status
   * @param msg The new value for msg
   * @param datanodes The new value for datanodes
   */
  public PutResponse(java.lang.Integer status, java.lang.CharSequence msg, java.util.List<java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> datanodes) {
    this.status = status;
    this.msg = msg;
    this.datanodes = datanodes;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return status;
    case 1: return msg;
    case 2: return datanodes;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: status = (java.lang.Integer)value$; break;
    case 1: msg = (java.lang.CharSequence)value$; break;
    case 2: datanodes = (java.util.List<java.util.Map<java.lang.CharSequence,java.lang.CharSequence>>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'status' field.
   * @return The value of the 'status' field.
   */
  public java.lang.Integer getStatus() {
    return status;
  }

  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(java.lang.Integer value) {
    this.status = value;
  }

  /**
   * Gets the value of the 'msg' field.
   * @return The value of the 'msg' field.
   */
  public java.lang.CharSequence getMsg() {
    return msg;
  }

  /**
   * Sets the value of the 'msg' field.
   * @param value the value to set.
   */
  public void setMsg(java.lang.CharSequence value) {
    this.msg = value;
  }

  /**
   * Gets the value of the 'datanodes' field.
   * @return The value of the 'datanodes' field.
   */
  public java.util.List<java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> getDatanodes() {
    return datanodes;
  }

  /**
   * Sets the value of the 'datanodes' field.
   * @param value the value to set.
   */
  public void setDatanodes(java.util.List<java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> value) {
    this.datanodes = value;
  }

  /**
   * Creates a new PutResponse RecordBuilder.
   * @return A new PutResponse RecordBuilder
   */
  public static youling.studio.protocol.PutResponse.Builder newBuilder() {
    return new youling.studio.protocol.PutResponse.Builder();
  }

  /**
   * Creates a new PutResponse RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PutResponse RecordBuilder
   */
  public static youling.studio.protocol.PutResponse.Builder newBuilder(youling.studio.protocol.PutResponse.Builder other) {
    return new youling.studio.protocol.PutResponse.Builder(other);
  }

  /**
   * Creates a new PutResponse RecordBuilder by copying an existing PutResponse instance.
   * @param other The existing instance to copy.
   * @return A new PutResponse RecordBuilder
   */
  public static youling.studio.protocol.PutResponse.Builder newBuilder(youling.studio.protocol.PutResponse other) {
    return new youling.studio.protocol.PutResponse.Builder(other);
  }

  /**
   * RecordBuilder for PutResponse instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PutResponse>
    implements org.apache.avro.data.RecordBuilder<PutResponse> {

    private int status;
    private java.lang.CharSequence msg;
    private java.util.List<java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> datanodes;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(youling.studio.protocol.PutResponse.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.status)) {
        this.status = data().deepCopy(fields()[0].schema(), other.status);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.msg)) {
        this.msg = data().deepCopy(fields()[1].schema(), other.msg);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.datanodes)) {
        this.datanodes = data().deepCopy(fields()[2].schema(), other.datanodes);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing PutResponse instance
     * @param other The existing instance to copy.
     */
    private Builder(youling.studio.protocol.PutResponse other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.status)) {
        this.status = data().deepCopy(fields()[0].schema(), other.status);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.msg)) {
        this.msg = data().deepCopy(fields()[1].schema(), other.msg);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.datanodes)) {
        this.datanodes = data().deepCopy(fields()[2].schema(), other.datanodes);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'status' field.
      * @return The value.
      */
    public java.lang.Integer getStatus() {
      return status;
    }

    /**
      * Sets the value of the 'status' field.
      * @param value The value of 'status'.
      * @return This builder.
      */
    public youling.studio.protocol.PutResponse.Builder setStatus(int value) {
      validate(fields()[0], value);
      this.status = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'status' field.
      * @return This builder.
      */
    public youling.studio.protocol.PutResponse.Builder clearStatus() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'msg' field.
      * @return The value.
      */
    public java.lang.CharSequence getMsg() {
      return msg;
    }

    /**
      * Sets the value of the 'msg' field.
      * @param value The value of 'msg'.
      * @return This builder.
      */
    public youling.studio.protocol.PutResponse.Builder setMsg(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.msg = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'msg' field has been set.
      * @return True if the 'msg' field has been set, false otherwise.
      */
    public boolean hasMsg() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'msg' field.
      * @return This builder.
      */
    public youling.studio.protocol.PutResponse.Builder clearMsg() {
      msg = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'datanodes' field.
      * @return The value.
      */
    public java.util.List<java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> getDatanodes() {
      return datanodes;
    }

    /**
      * Sets the value of the 'datanodes' field.
      * @param value The value of 'datanodes'.
      * @return This builder.
      */
    public youling.studio.protocol.PutResponse.Builder setDatanodes(java.util.List<java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> value) {
      validate(fields()[2], value);
      this.datanodes = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'datanodes' field has been set.
      * @return True if the 'datanodes' field has been set, false otherwise.
      */
    public boolean hasDatanodes() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'datanodes' field.
      * @return This builder.
      */
    public youling.studio.protocol.PutResponse.Builder clearDatanodes() {
      datanodes = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PutResponse build() {
      try {
        PutResponse record = new PutResponse();
        record.status = fieldSetFlags()[0] ? this.status : (java.lang.Integer) defaultValue(fields()[0]);
        record.msg = fieldSetFlags()[1] ? this.msg : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.datanodes = fieldSetFlags()[2] ? this.datanodes : (java.util.List<java.util.Map<java.lang.CharSequence,java.lang.CharSequence>>) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PutResponse>
    WRITER$ = (org.apache.avro.io.DatumWriter<PutResponse>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PutResponse>
    READER$ = (org.apache.avro.io.DatumReader<PutResponse>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
