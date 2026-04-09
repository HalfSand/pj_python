from pathlib import Path

import pandas as pd


REQUIRED_COLUMNS = {"day", "study_hours"}


def load_data(csv_path: Path) -> pd.DataFrame:
    """Load and validate CSV data."""
    if not csv_path.exists():
        raise FileNotFoundError(f"Input file not found: {csv_path}")

    df = pd.read_csv(csv_path)

    missing = REQUIRED_COLUMNS - set(df.columns)
    if missing:
        raise ValueError(
            "CSV missing required columns: " + ", ".join(sorted(missing))
        )

    # Validate numeric values in study_hours
    numeric_hours = pd.to_numeric(df["study_hours"], errors="coerce")
    invalid_rows = df[numeric_hours.isna()].index.tolist()
    if invalid_rows:
        row_numbers = [i + 2 for i in invalid_rows]  # +2 for header + 1-based index
        raise ValueError(
            "Invalid numeric values in 'study_hours' at CSV row(s): "
            + ", ".join(map(str, row_numbers))
        )

    df["study_hours"] = numeric_hours.astype(float)
    return df
