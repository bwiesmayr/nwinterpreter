measA = readtable('BenchmarkProject.BenchmarkProjectApp.Ex7a.permit.EI.opsem.csv');
measB = readtable('BenchmarkProject.BenchmarkProjectApp.Ex7b.permit.EI.opsem.csv');
measC = readtable('BenchmarkProject.BenchmarkProjectApp.Ex7c.permit.EI.opsem.csv');
measD = readtable('BenchmarkProject.BenchmarkProjectApp.Ex7d.permit.EI.opsem.csv');
        % access table cell content: 
        % currmeas{i,1} for repetition
        % currmeas{i,7} for duration
        % currmeas{i,3} for number of loop runs



results_avg = zeros(13,4);
results_std = zeros(13,4);
x = zeros(13,4);
[results_avg(:,1), results_std(:,1), x(:,1)] = evaluate_data(measA);
[results_avg(:,2), results_std(:,2), x(:,2)] =evaluate_data(measB);
[results_avg(:,3), results_std(:,3), x(:,3)] =evaluate_data(measC);
[results_avg(:,4), results_std(:,4), x(:,4)] =evaluate_data(measD);


function [results_avg, results_std, x] = evaluate_data(meas)
n = 1;
for i = 26:25:350
    % discard first 5 measurements, evaluate subsequent 20
    curr_meas = meas{i+5:i+24,7};
    results_avg(n) = mean(curr_meas) / 10^9; % in s
    results_std(n) = std(curr_meas) / 10^9; % in s
    x(n) =  meas{i,3}; % curr loop runs
    n = n+1;
end
end


figure;
hold on;
errorbar(x, results_avg, results_std, 'X', 'DisplayName', 'Average Results with Error Bars');
xlabel('Number of Loop Runs');
ylabel('Execution Time (s)');
ax = gca;
ax.XScale = 'log';
xlim([0.8,220]);
ylim([0 4]);
legend({'Variant A', 'Variant B', 'Variant C', 'Variant D'});
legend show;
grid on;
hold off;

figure;
hold on;
errorbar(x, results_avg, results_std, 'X', 'DisplayName', 'Average Results with Error Bars');
xlabel('Number of Loop Runs');
ylabel('Execution Time (s)');
ax = gca;
ax.XScale = 'log';
xlim([0.8,120]);
ylim([0 1.75]);
legend({'Variant A', 'Variant B', 'Variant C', 'Variant D'});
legend show;
grid on;
hold off;