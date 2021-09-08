while (<>) {
print if /(^cat$|^cat\W|\Wcat$|\Wcat\W)/ ;
}