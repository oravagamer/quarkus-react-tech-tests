export const frontendUrl = location.toString();
export const backendUrl = new URL("/rest/api", frontendUrl);