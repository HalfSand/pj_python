import argparse
import json
import sys
from pathlib import Path

from analyze import calculate_stats
from load_data import load_data
from plot import plot_data


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description="Study hours analyzer")
    parser.add_argument(
        "--input",
        type=str,
        default="data/study_hours.csv",
        help="Path to input CSV (relative to project root)",
    )
    parser.add_argument(
        "--outdir",
        type=str,
        default="output",
        help="Output directory (relative to project root)",
    )
    return parser.parse_args()


def main():
    args = parse_args()

    project_root = Path(__file__).resolve().parent.parent
    input_path = project_root / args.input
    outdir = project_root / args.outdir
    outdir.mkdir(parents=True, exist_ok=True)

    chart_path = outdir / "study_hours.png"
    report_path = outdir / "report.json"

    try:
        df = load_data(input_path)
        stats = calculate_stats(df)

        print("=== Study Data ===")
        print(df)
        print("\n=== Stats ===")
        for key, value in stats.items():
            print(f"{key}: {value}")

        plot_data(df, chart_path)

        with report_path.open("w", encoding="utf-8") as f:
            json.dump(stats, f, ensure_ascii=False, indent=2)

        print(f"\nChart saved to: {chart_path}")
        print(f"Report saved to: {report_path}")

    except FileNotFoundError as e:
        print(f"[ERROR] {e}")
        sys.exit(1)
    except ValueError as e:
        print(f"[ERROR] {e}")
        sys.exit(1)
    except Exception as e:
        print(f"[UNEXPECTED ERROR] {e}")
        sys.exit(1)


if __name__ == "__main__":
    main()
