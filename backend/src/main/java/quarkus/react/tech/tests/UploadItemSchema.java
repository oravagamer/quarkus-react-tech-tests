package quarkus.react.tech.tests;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(type = SchemaType.STRING, format = "binary")
public class UploadItemSchema {
}
