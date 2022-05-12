class TestThis {
	int y;
	TestThis() {
		y=10;
	}
	TestThis(int newY) {
		this();
		y = y + newY;
	}
	int max(int n1, int n2){
		if (n1 > n2) return n1;
		else return n2;
	}
	int max(int n1, int n2, int n3) {
		int temp = this.max(n1, n2);
		return this.max(temp, n3);
	}
	int changeY(int y) {
		this.y = y;
		return this.y;
	}
	int getY() {
		return y;
	}
	int exchange() {
		int y = 5;
		return this.y + y;
	}
}