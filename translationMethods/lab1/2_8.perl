while (<>) {
s/\b([1-9])(\d*)(0)\b/$1$2/g;
print;
}