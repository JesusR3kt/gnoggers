package engine.math;

public class Matrix2f {
	
	public float m00, m01;
	public float m10, m11;
	
	public Matrix2f() {
		setIdentity();
	}

	public void setIdentity() {
		m00 = 1.0f;
		m01 = 0.0f;
		m10 = 0.0f;
		m11 = 1.0f;
	}
	
	public void setZero() {
		m00 = 0.0f;
		m01 = 0.0f;
		m10 = 0.0f;
		m11 = 0.0f;
	}
	
	public void setRotation(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		
		m00 = cos;
		m01 = -sin;
		m10 = sin;
		m11 = cos;
	}
	
	public Matrix2f transpose(Matrix2f dest) {
		if (dest == null) dest = new Matrix2f();
		dest.m00 = m00;
		dest.m01 = m10;
		dest.m10 = m01;
		dest.m11 = m11;
		return dest;
	}
	
	public Vector2f mul(Vector2f src, Vector2f dest) {
		if (dest == null) dest = new Vector2f();
		dest.x = m00 * src.x + m01 * src.y;
		dest.y = m10 * src.y + m11 * src.y;
		return dest;
	}
	
	public Vector2f mul(Vector2f src) {
		return mul(src, src);
	}
	
	public String toString() {
		return String.format("| %10f %10f |\n| %10f %10f |", m00, m01, m10, m11);
	}
}
