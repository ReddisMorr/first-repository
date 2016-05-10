package com.example.bubblebreaker;

public class BBMatrix {
	public BBMatrix(int pViewWidth, int pViewHeight, BBSettings settings) {
		this.mBBSettings=settings;
		mXBubbleCount = (int) Math.floor(pViewWidth
				/ mBBSettings.getBubble_diameter());
		mYBubbleCount = (int) Math.floor(pViewHeight
				/ mBBSettings.getBubble_diameter());

		mBubbleGrid = new int[mXBubbleCount][mYBubbleCount];
		mBubbleMarked = new boolean[mXBubbleCount][mYBubbleCount];

		clearBubbles();

		fillBBMatrix();
	}

	public void fillBBMatrix() {
		for (int i = 0; i < mXBubbleCount; i++) {
			for (int j = 0; j < mYBubbleCount; j++) {
				newBubble(i, j,
						(int) (Math.random() * mBBSettings.getBubble_color()));
			}
		}
	}

	public void newBubble(int x, int y, int pBubbleType) {
		mBubbleGrid[x][y] = pBubbleType;
		mBubbleMarked[x][y] = false;
	}

	public void findSameBubble(int x, int y) {

		if ((x < 0 || y < 0 || x >= mXBubbleCount || y >= mYBubbleCount)
				|| (mBubbleGrid[x][y] == BBConstants.NULL_BUBBLE)) {
			return;
		}

		mSameBubbleCount++;
		mBubbleMarked[x][y] = true;

		int color = mBubbleGrid[x][y];

		if (getColor(x, y + 1) == color && isMarked(x, y + 1) == false) {
			findSameBubble(x, y + 1);
		}
		if (getColor(x, y - 1) == color && isMarked(x, y - 1) == false) {
			findSameBubble(x, y - 1);
		}
		if (getColor(x + 1, y) == color && isMarked(x + 1, y) == false) {
			findSameBubble(x + 1, y);
		}
		if (getColor(x - 1, y) == color && isMarked(x - 1, y) == false) {
			findSameBubble(x - 1, y);
		}
	}

	public boolean isEmptyColumn(int x) {
		return isNullBubble(x, mYBubbleCount - 1);
	}

	public void removeMarkedBubbles() {
		// remove marked bubbles
		for (int y = 0; y < mYBubbleCount; y++) {
			for (int x = 0; x < mXBubbleCount; x++) {
				if (isMarked(x, y) == true) {
					removeBubble(x, y);
					for (int i = y; i > 0; i--) {
						moveBubble(x, i - 1, x, i);
					}
				}
			}
		}

		// remove empty columns
		for (int x = 0; x < mXBubbleCount; x++) {
			// check the empty column
			if (isEmptyColumn(x) == true) {
				int i = x + 1;
				for (; i < mXBubbleCount && isEmptyColumn(i) == true; i++) {
					;
				}
				if (i < mXBubbleCount) {
					for (int y = 0; y < mYBubbleCount; y++) {
						moveBubble(i, y, x, y);
					}
				}
			}
		}
	}

	public void removeBubble(int x, int y) {
		if (isNullBubble(x, y) == false) {
			mBubbleGrid[x][y] = BBConstants.NULL_BUBBLE;
		}
	}

	public void removeMark() {
		// reset the same bubble count
		mSameBubbleCount = 0;

		for (int i = 0; i < mXBubbleCount; i++) {
			for (int j = 0; j < mYBubbleCount; j++) {
				mBubbleMarked[i][j] = false;
			}
		}
	}

	public void moveBubble(int x, int y, int toX, int toY) {
		if (x != toX || y != toY) {
			mBubbleGrid[toX][toY] = mBubbleGrid[x][y];
			mBubbleGrid[x][y] = BBConstants.NULL_BUBBLE;
		}
	}

	public boolean isNullBubble(int x, int y) {
		if ((x < 0 || y < 0 || x >= mXBubbleCount || y >= mYBubbleCount)
				|| BBConstants.NULL_BUBBLE == (mBubbleGrid[x][y])) {
			return true;
		}

		return false;
	}

	public int getColor(int x, int y) {
		if (isNullBubble(x, y) == true) {
			return BBConstants.NULL_BUBBLE;
		} else {
			return mBubbleGrid[x][y];
		}
	}

	public boolean isMarked(int x, int y) {
		if (isNullBubble(x, y) == true) {
			return false;
		} else {
			return mBubbleMarked[x][y];
		}
	}

	public void clearBubbles() {
		for (int x = 0; x < mXBubbleCount; x++) {
			for (int y = 0; y < mYBubbleCount; y++) {
				setBubble(x, y, BBConstants.NULL_BUBBLE);
			}
		}
	}

	public void setBubble(int x, int y, int bubbleIndex) {
		mBubbleGrid[x][y] = bubbleIndex;
	}

	public boolean isBBMatrixSolvable() {
		for (int y = mYBubbleCount - 1; y > -1; y--) {
			for (int x = 0; x < mXBubbleCount; x++) {
				if (isNullBubble(x, y) == false) {
					if (isNullBubble(x + 1, y) == false
							&& mBubbleGrid[x][y] == mBubbleGrid[x + 1][y]) {
						return true;
					}
					if (isNullBubble(x, y - 1) == false
							&& mBubbleGrid[x][y] == mBubbleGrid[x][y - 1]) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * A two-dimensional array of integers in which the number represents the
	 * index of the bubble that should be drawn at that locations
	 */
	public int[][] mBubbleGrid;

	/**
	 * A two-dimensional array of integers in which the number is selected
	 */
	public boolean[][] mBubbleMarked;
	public int mSameBubbleCount;
    public BBSettings mBBSettings;
	public int mXBubbleCount;
	public int mYBubbleCount;
}
