package engine.math;

public class Vector2f {

	public float x;
	public float y;
	
	public Vector2f() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f(Vector2f vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public float max() {
		return Math.max(x, y);
	}
	
	public float dot(Vector2f vector) {
		return x * vector.x + y * vector.y;
	}
	
	public void normalise() {
		float length = length();
		x /= length;
		y /= length;
	}
	
	public static Vector2f add(Vector2f left, Vector2f right, Vector2f dest) {
		if (dest == null) return new Vector2f(left.x + right.x, left.y + right.y);
		dest.set(left.x + right.x, left.y + right.y);
		return dest;
	}
	
	public void add(Vector2f vector, float value) {	
		x = x + vector.x * value;
		y = y + vector.y * value;
	}
	
	public Vector2f negate() {
		x = -x;
		y = -y;
		return this;
	}
	
	public Vector2f negate(Vector2f dest) {
		if (dest == null) return new Vector2f(-x, -y);
		dest.x = -x;
		dest.y = -y;
		return dest;
	}
	
	public Vector2f add(Vector2f vector) {
		x += vector.x;
		y += vector.y;
		return this;
	}
	
	public Vector2f add(float value) {
		x += value;
		y += value;
		return this;
	}
	
	public static Vector2f sub(Vector2f left, Vector2f right, Vector2f dest) {
		if (dest == null) return new Vector2f(left.x - right.x, left.y - right.y);
		dest.set(left.x - right.x, left.y - right.y);
		return dest;
	}
	
	public Vector2f sub(Vector2f vector) {
		x -= vector.x;
		y -= vector.y;
		return this;
	}
	
	public Vector2f sub(float value) {
		x -= value;
		y -= value;
		return this;
	}
	
	public Vector2f mul(Vector2f vector) {
		x *= vector.x;
		y *= vector.y;
		return this;
	}
	
	public Vector2f mul(float value) {
		x *= value;
		y *= value;
		return this;
	}
	
	public static Vector2f mul(Vector2f src, float value, Vector2f dest) {
		if (dest == null) return new Vector2f(src.x * value, src.y * value);
		dest.set(src.x * value, src.y * value);
		return dest;
	}
	
	public Vector2f div(Vector2f vector) {
		x /= vector.x;
		y /= vector.y;
		return this;
	}
	
	public Vector2f div(float value) {
		x /= value;
		y /= value;
		return this;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2f vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public Vector2f left() {
		return new Vector2f(this.y, -this.x);
	}

	public static Vector2f cross(float value, Vector2f src, Vector2f dest) {
		if (dest == null) return new Vector2f(src.y * -value, src.x * value);
		dest.set(src.y * -value, src.x * value);
		return dest;
	}

	public float cross(Vector2f vector) {
		return x * vector.y - y * vector.x;
	}
	
	public static float cross(Vector2f left, Vector2f right) {
		return left.x * right.y - left.y * right.x;
	}

	public float lengthSq() {
		return x * x + y * y;
	}
}
