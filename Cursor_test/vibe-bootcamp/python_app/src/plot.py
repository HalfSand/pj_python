from pathlib import Path

import matplotlib.pyplot as plt
import pandas as pd


def plot_data(df: pd.DataFrame, output_path: Path) -> None:
    """Plot study hours and save figure."""
    plt.figure(figsize=(8, 4))
    plt.plot(df["day"], df["study_hours"], marker="o")
    plt.title("My Weekly Study Hours")
    plt.xlabel("Day")
    plt.ylabel("Hours")
    plt.grid(True)
    plt.tight_layout()
    plt.savefig(output_path)
    plt.show()
