package com.example.employeeapi.dto;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

public class ObjectIdCodec implements Codec<ObjectId> {
    @Override
    public void encode(BsonWriter writer, ObjectId value, EncoderContext encoderContext) {
        writer.writeObjectId(value);
    }

    @Override
    public ObjectId decode(BsonReader reader, DecoderContext decoderContext) {
        if (reader.getCurrentBsonType() == BsonType.OBJECT_ID) {
            return reader.readObjectId();
        } else {
            throw new IllegalArgumentException("Can't decode value with type: " + reader.getCurrentBsonType());
        }
    }

    @Override
    public Class<ObjectId> getEncoderClass() {
        return ObjectId.class;
    }
}
