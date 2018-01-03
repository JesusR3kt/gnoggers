package engine.math;

public class Vector3f {

	public float x;
	public float y;
	public float z;
	
	public Vector3f() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(Vector3f vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public Vector3f(Vector4f vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(Vector3f vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public float max() {
		return Math.max(x, Math.max(y, z));
	}
	
	public float dot(Vector3f vector) {
		return x * vector.x + y * vector.y + z * vector.z;
	}
	
	public void normalise() {
		float length = length();
		x /= length;
		y /= length;
		z /= length;
	}
	
	public static Vector3f add(Vector3f left, Vector3f right, Vector3f dest) {
		if (dest == null) dest = new Vector3f();
		dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
		return dest;
	}
	
	public static Vector3f sub(Vector3f left, Vector3f right, Vector3f dest) {
		if (dest == null) dest = new Vector3f();
		dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
		return dest;
	}

	public Vector3f scale(float value) {
		x *= value;
		y *= value;
		z *= value;
		return this;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	public Vector3f negate() {
		return negate(this);
	}
	
	public Vector3f negate(Vector3f dest) {
		if (dest == null) dest = new Vector3f();
		dest.set(-x, -y, -z);
		return dest;
	}
	
	public static Vector3f cross(Vector3f left, Vector3f right, Vector3f dest) {
		if (dest == null) dest = new Vector3f();
		dest.set(left.y * right.z - left.z * right.y,
				right.x * left.z - right.z * left.x,
				left.x * right.y - left.y * right.x);
		return dest;
	}
}
