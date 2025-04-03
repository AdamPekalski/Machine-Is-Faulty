import pandas as pd
import os
import matplotlib.pyplot as plt
import seaborn as sns

# Set style for better visualization
plt.style.use('default')
sns.set_theme(style="whitegrid")
sns.set_palette("husl")

# Get the absolute path to the CSV file
current_dir = os.path.dirname(os.path.abspath(__file__))
csv_path = os.path.join(os.path.dirname(os.path.dirname(current_dir)), 'src', 'data', 'predictive_dataset.csv')

# Read the dataset
df = pd.read_csv(csv_path)

# Create output directory if it doesn't exist
output_dir = os.path.join(os.path.dirname(current_dir), 'visualizations')
os.makedirs(output_dir, exist_ok=True)

# Create a figure for individual feature distributions
plt.figure(figsize=(15, 10))
plt.suptitle('Feature Distributions', fontsize=16, y=1.05)

for idx, column in enumerate(df.columns, 1):
    plt.subplot(3, 2, idx)
    sns.countplot(data=df, x=column)
    plt.title(f'{column} Distribution')
    plt.xticks(rotation=45)

plt.tight_layout()
plt.savefig(os.path.join(output_dir, 'feature_distributions.png'))
plt.close()

# Create heatmap of correlations
plt.figure(figsize=(10, 8))
# Convert boolean strings to integers for correlation
df_numeric = df.apply(lambda x: pd.factorize(x)[0])
correlation_matrix = df_numeric.corr()
sns.heatmap(correlation_matrix, annot=True, cmap='coolwarm', center=0)
plt.title('Correlation Heatmap')
plt.tight_layout()
plt.savefig(os.path.join(output_dir, 'correlation_heatmap.png'))
plt.close()

# Create subplots for feature relationships with MachineIsFaulty
plt.figure(figsize=(15, 10))
plt.suptitle('Feature Relationships with Machine Faults', fontsize=16, y=1.05)

for idx, column in enumerate(df.columns[:-1], 1):
    plt.subplot(2, 2, idx)
    sns.countplot(data=df, x=column, hue='MachineIsFaulty')
    plt.title(f'{column} vs MachineIsFaulty')
    plt.xticks(rotation=45)

plt.tight_layout()
plt.savefig(os.path.join(output_dir, 'feature_relationships.png'))
plt.close()

# Print the original frequency tables
print("\nFrequency Tables for Each Feature:")
print("==================================")
for column in df.columns:
    print(f"\n{column}:")
    print(df[column].value_counts())
    print("-" * 30)

# Print cross-tabulations
print("\nCross-tabulation with MachineIsFaulty:")
print("=====================================")
for column in df.columns[:-1]:  # Exclude MachineIsFaulty itself
    print(f"\n{column} vs MachineIsFaulty:")
    print(pd.crosstab(df[column], df['MachineIsFaulty']))
    print("-" * 30)

print("\nGraphs have been saved in the 'analysis/visualizations' directory:")
print("1. feature_distributions.png - Shows the distribution of each feature")
print("2. correlation_heatmap.png - Shows the correlation between features")
print("3. feature_relationships.png - Shows how each feature relates to machine faults")