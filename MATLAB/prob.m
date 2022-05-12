function prob = prob
prob.C=@C;
end

%% nchoosek
function out = C(n,k)
out = factorial(n) / (factorial(n - k) * factorial(k));
end