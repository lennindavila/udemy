package com.bolsadeideas.springboot.backend.apirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpAIBAAKCAQEA1IaGV8DMaElnUTXjqKGErLGD7jA7/RGp90PGzfldJ4CTPc3f\r\n"
			+ "tX1f0uf/QXfXLfuyXopzDpDP0h2VmZtammraIjf2nRUw0kPErSCjHS+IrL+8Bsbm\r\n"
			+ "K1MwG9wBlUUpTv/Rxc0QHgN0ld8OFOOURE241URZu2mj0UaJAIQxOdhPO4iOy1b/\r\n"
			+ "kpqzYBRQ4igszcLenr6KQphVHZeg9G0eptM/2dNc6lH38cGV10KkWtBWxvGg9EIO\r\n"
			+ "UJnR51IVEaJrRBnLpRTbpmgW7zNpLtAvVtNbGej/BbTsfw0mpWeq0dtT830oRnut\r\n"
			+ "H5ZCUiJHRqtUqwpe1IvnCHwiDyBKX/QJ9TPy1wIDAQABAoIBAQCqnyHl+8RfiWbo\r\n"
			+ "HRKcBKhmmNGpfZH7t520BPbr0GJIX4JWoR0UMQaoZVdZsSPRJ/xoyFBdBkfCkbvU\r\n"
			+ "KZRQ2q4rwLA+JpAejwesUsKBoPLidcA31KFfcRbzluqyc2cZpGo+mkZxQibsV7qP\r\n"
			+ "bLJRL/DPT2OXAdXiIvXEs1xt60qZKK5jfcRBmIaIWXSEb3IrnMoBSPm5xw0sXBdg\r\n"
			+ "y7EoLfGxCZW9xdQbmBk1u3RboLwaOex4zzj1mdJOyIkvK2ZZW/2qhESWiV3tDyj6\r\n"
			+ "dU9unU9S9LWUkv1PyFfA3Ff81tX8G8wLrSkTcmgGWdItdAmiNVad/ZV57gyDagqU\r\n"
			+ "7CWS9tT5AoGBAP1hJ+Byz2nM6hGudRH9LfYnr1o8lS0TufekoAY1/GEcThVPXJo3\r\n"
			+ "UnWnpXbafv2eWgb95JIFO+7p8R8lTuoPpHiAjbxwS07LMYUzOGuTUugqYD1h/URG\r\n"
			+ "mHkKKYEDGykNUzPzBA4v6uyEUgIEMis8T5Yj6mwpcEyXfkN5h6kT2FTbAoGBANa5\r\n"
			+ "NFVzcsDoU5XI3gfH0G4TvUL+0njc3j3cojc8QI4QpFyfZxz2Cmy8ekbepnebdSKs\r\n"
			+ "5uikFMDYaoMHpy37LLscneWfttPB6bfpozp6abjs0a7qYvGh/ufDXRCm5KA40NTp\r\n"
			+ "b8OjinJdpjDbbhPmv3NqP/exqu9DGhf+LZ7RaBy1AoGANtmQU1MnOUUFqpw0dZd0\r\n"
			+ "GqROnl8c/+wURtqZVZ7cKn2BHbXXWv7818FZ3VfBQ8/Ct17A2ydM6RQ1gUOHSL3o\r\n"
			+ "IbnadN9uPflYl/gVj5wNx3pc6QzMUbpiiVk80NPGTmW3DDjgON4AogCm7A7SeW+N\r\n"
			+ "3veZaJ6z07C7wYmOZ3iHTR8CgYAmtCl3waCMnxP56sQCuQSb4tj5i38apA03L17Z\r\n"
			+ "//VO5jcKLv1iHgNRCEQuJBHze0UI5AuXIYRQInJIfgenZR/x9j2L11cYIlrjeKyg\r\n"
			+ "bNJDsAQ3uC3vihitY+yRhNOvAa66wf0QUZkx9ilvow3bbntR7XPsTM0X5Yt9iaZl\r\n"
			+ "ey9+BQKBgQCRoVA1qVjV28L97sL6/d0/1VI0rFr//py085+ysz0FK1NT5SwnQXDQ\r\n"
			+ "CE1zf+NDnbFYwu3UpP9OktpVRgfpG8J4lXoSGX/D1uaEa/PKFWU2FCpVlb39z6h/\r\n"
			+ "qUbljHlHpSPtUCioUP7YVvwa2BQ+JVgIbJTvAdUa+bYIgKv4mnrWTQ==\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1IaGV8DMaElnUTXjqKGE\r\n"
			+ "rLGD7jA7/RGp90PGzfldJ4CTPc3ftX1f0uf/QXfXLfuyXopzDpDP0h2VmZtammra\r\n"
			+ "Ijf2nRUw0kPErSCjHS+IrL+8BsbmK1MwG9wBlUUpTv/Rxc0QHgN0ld8OFOOURE24\r\n"
			+ "1URZu2mj0UaJAIQxOdhPO4iOy1b/kpqzYBRQ4igszcLenr6KQphVHZeg9G0eptM/\r\n"
			+ "2dNc6lH38cGV10KkWtBWxvGg9EIOUJnR51IVEaJrRBnLpRTbpmgW7zNpLtAvVtNb\r\n"
			+ "Gej/BbTsfw0mpWeq0dtT830oRnutH5ZCUiJHRqtUqwpe1IvnCHwiDyBKX/QJ9TPy\r\n"
			+ "1wIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
	
	

}
