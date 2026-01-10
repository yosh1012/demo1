/**
 * Outputs error messages
 * @param: message
 */
export function errorMessage(message: string): never {
    throw new Error(`ERROR: ${message}`);
}