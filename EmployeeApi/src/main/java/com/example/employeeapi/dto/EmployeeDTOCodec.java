package com.example.employeeapi.dto;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

public class EmployeeDTOCodec implements Codec<EmployeeDTO> {

    private CodecRegistry registry;

    public EmployeeDTOCodec(CodecRegistry codecRegistry) {
        this.registry=codecRegistry;
    }

    public void setRegistry(CodecRegistry registry) {
        this.registry = registry;
    }
    @Override
    public EmployeeDTO decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();

        // Read properties from BSON
        ObjectId employeeId = null;
        String name = null;
        Integer age = null;
        String title = null;

        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            switch (fieldName) {
                case "_id":
                    employeeId = reader.readObjectId();
                    break;
                case "name":
                    name = reader.readString();
                    break;
                case "age":
                    age = reader.readInt32();
                    break;
                case "title":
                    title = reader.readString();
                    break;
                default:
                    reader.skipValue();
            }
        }

        reader.readEndDocument();

        return new EmployeeDTO(employeeId, name, age, title);
    }

    @Override
    public void encode(BsonWriter writer, EmployeeDTO value, EncoderContext encoderContext) {
        writer.writeStartDocument();

        // Write properties to BSON
        writer.writeObjectId("_id", value.getEmployeeId());
        writer.writeString("name", value.getName());
        writer.writeInt32("age", value.getAge());
        writer.writeString("title", value.getTitle());

        writer.writeEndDocument();
    }

    @Override
    public Class<EmployeeDTO> getEncoderClass() {
        return EmployeeDTO.class;
    }
}
