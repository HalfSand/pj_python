import pandas as pd


def calculate_stats(df: pd.DataFrame) -> dict:
    """Calculate basic statistics for study_hours column."""
    series = df["study_hours"]
    return {
        "count": int(series.count()),
        "mean": float(series.mean()),
        "max": float(series.max()),
        "min": float(series.min()),
        "median": float(series.median()),
    }
