package nl.groep4.kvc.server.util.serilize;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    public JsonElement serialize(T object, Type interfaceType, JsonSerializationContext context) {
	JsonObject wrapper = new JsonObject();
	wrapper.addProperty("type", getClassnameFromString(object.getClass()));
	wrapper.add("data", context.serialize(object));
	return wrapper;
    }

    public T deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context)
	    throws JsonParseException {
	if (elem instanceof JsonObject) {
	    JsonObject wrapper = (JsonObject) elem;
	    JsonElement typeName = get(wrapper, "type");
	    JsonElement data = get(wrapper, "data");
	    Type actualType;
	    try {
		actualType = typeForName(typeName.getAsString());
	    } catch (ClassNotFoundException e) {
		throw new JsonParseException(e);
	    }
	    return context.deserialize(data, actualType);
	} else {
	    System.err.printf("Not deserilizing correctly! -> %s;%s\n", elem.toString(), interfaceType.toString());
	    return context.deserialize(elem, interfaceType);
	}
    }

    protected Type typeForName(String className) throws ClassNotFoundException {
	return Class.forName(className);

    }

    protected String getClassnameFromString(Class<?> clss) {
	return clss.getName();
    }

    protected JsonElement get(JsonObject wrapper, String memberName) {
	JsonElement elem = wrapper.get(memberName);
	if (elem == null)
	    throw new JsonParseException(
		    "no '" + memberName + "' member found in what was expected to be an interface wrapper");
	return elem;
    }
}